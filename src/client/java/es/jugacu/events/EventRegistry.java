package es.jugacu.events;

import es.jugacu.Hack;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

interface EventCallback {
    void apply(Object... args) throws InvocationTargetException, IllegalAccessException;
}

public class EventRegistry {
    private static EventRegistry instance;

    private final HashMap<EventType, ArrayList<EventCallback>> eventMap = new HashMap<>();

    public static EventRegistry getInstance() {
        if (instance == null) {
            instance = new EventRegistry();
        }

        return instance;
    }

    private EventRegistry() {
    }

    public void fire(EventType type, Object... args) {
        ArrayList<EventCallback> eventCallbacks = eventMap.get(type);

        if (eventCallbacks == null) {
            return;
        }

        for (EventCallback callback : eventCallbacks) {
            try {
                callback.apply(args);
            } catch (InvocationTargetException | IllegalAccessException e) {
                Hack.LOGGER.error(e.toString());
            }
        }
    }

    public void registerEvents(Object obj) {
        Class<?> clazz = obj.getClass();

        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            if (!method.isAnnotationPresent(Event.class)) {
                continue;
            }

            Event annotation = method.getAnnotation(Event.class);
            EventType type = annotation.type();

            ArrayList<EventCallback> eventCallbacks = eventMap.get(type);

            EventCallback callback = (Object... args) -> {
                method.invoke(obj, args);
            };

            if (eventCallbacks == null) {
                eventCallbacks = new ArrayList<>(List.of(callback));
                eventMap.put(type, eventCallbacks);

                return;
            }

            eventCallbacks.add(callback);
        }
    }

}
