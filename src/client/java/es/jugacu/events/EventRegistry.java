package es.jugacu.events;

import es.jugacu.Hack;
import es.jugacu.utils.Tuple;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventRegistry {
    private static EventRegistry instance;

    private final HashMap<EventType, ArrayList<Tuple<Method, Object>>> eventCallbackMap = new HashMap<>();

    public static EventRegistry getInstance() {
        if (EventRegistry.instance == null) {
            EventRegistry.instance = new EventRegistry();
        }

        return EventRegistry.instance;
    }

    private EventRegistry() {
    }

    public void fire(EventType type, Object... args) {
        ArrayList<Tuple<Method, Object>> eventCallbacks = eventCallbackMap.get(type);

        if (eventCallbacks == null) {
            return;
        }

        for (Tuple<Method, Object> tuple : eventCallbacks) {
            try {
                Method method = tuple.getFirst();
                Object object = tuple.getSecond();

                method.invoke(object, args);
            } catch (InvocationTargetException | IllegalAccessException e) {
                Hack.LOGGER.error(e.getCause().toString());
                e.getCause().printStackTrace();
            }
        }
    }

    public EventRegistry remove(Object object) {
        for (HashMap.Entry<EventType, ArrayList<Tuple<Method, Object>>> entry : eventCallbackMap.entrySet()) {
            ArrayList<Tuple<Method, Object>> arrayList = entry.getValue();

            // Check if the object is equal to the one we want to remove
            // If so, remove it from the ArrayList
            arrayList.removeIf(tuple -> tuple.getSecond().equals(object));
        }

        return this;
    }

    public EventRegistry remove(EventType event) {
        this.eventCallbackMap.remove(event);

        return this;
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

            ArrayList<Tuple<Method, Object>> eventCallbacks = this.eventCallbackMap.get(type);

            Tuple<Method, Object> tuple = new Tuple<>(method, obj);

            if (eventCallbacks == null) {
                eventCallbacks = new ArrayList<>(List.of(tuple));
                this.eventCallbackMap.put(type, eventCallbacks);

                return;
            }

            eventCallbacks.add(tuple);
        }
    }

}
