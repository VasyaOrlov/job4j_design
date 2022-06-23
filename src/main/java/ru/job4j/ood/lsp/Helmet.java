package ru.job4j.ood.lsp;

/**
 * класс демонстрирует нарушение принципа LSP
 */
public class Helmet extends Equipment {

    /**
     * не учитывается инвариант родительского класса.
     * в родительском классе в конструктуре перед инициализацией поля armor происходит валидация.
     * здесь валидации инициализирующего значения нет - условие базового класса не сохранено
     */
    public Helmet(int armor) {
        super(armor);
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
}
