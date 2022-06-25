package ru.job4j.ood.lsp;

/**
 * класс демонстрирует нарушение принципа LSP
 */
public class Helmet extends Equipment {

    public Helmet(int armor) {
        super(armor);
    }

    /**
     * не учитывается инвариант родительского класса.
     * здесь нет валидации инициализирующего значения.
     * возможно не валидное состояние
     */
    @Override
    public void setArmor(int armor) {
        this.armor = armor;
    }

    /**
     * предусловие усилено в подклассе
     */
    @Override
    public void fight() {
        if (getArmor() < 5) {
            throw new IllegalArgumentException("недостаточно брони");
        }
    }

    /**
     * постусловие ослаблено в подклассе
     */
    @Override
    public boolean win() {
        return getArmor() >= 0;
    }

    public static void main(String[] args) {
        Helmet h = new Helmet(3);
        h.setArmor(-1);
        System.out.println(h.getArmor());
    }
}
