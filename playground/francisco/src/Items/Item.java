package Items;

import Utils.ItemType;

public class Item {
    protected String name;
    protected ItemType itemType;

    public String getName() {
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public ItemType getItemType(){
        return this.itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}
