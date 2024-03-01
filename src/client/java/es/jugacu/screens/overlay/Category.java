package es.jugacu.screens.overlay;

import net.minecraft.text.Text;

import java.util.ArrayList;

public class Category {
    public final Text name;
    public final ArrayList<CategoryItem> items;

    public Category(Text name, ArrayList<CategoryItem> items) {
        this.name = name;
        this.items = items;
    }

    public Text getName() {
        return name;
    }

    public ArrayList<CategoryItem> getItems() {
        return items;
    }
}
