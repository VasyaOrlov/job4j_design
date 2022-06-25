package ru.job4j.ood.lsp;

public class Equipment {
    protected int armor;

    public Equipment(int armor) {
        validate(armor);
        this.armor = armor;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        validate(armor);
        this.armor = armor;
    }

    public void fight() {
        if (this.armor < 1) {
            throw new IllegalArgumentException("недостаточно брони");
        }
    }

    public boolean win() {
       return this.armor > 0;
    }

    public void validate(int armor) {
        if (!(armor > 0)) {
            throw new IllegalArgumentException("броня экипировки должна быть больше 0");
        }
    }
}




