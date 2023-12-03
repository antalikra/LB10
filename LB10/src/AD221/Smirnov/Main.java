package AD221.Smirnov;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

// Оновлений інтерфейс DataStructure з методом iterator()
interface DataStructure<T> extends Iterable<T> {
    void push(T element);
    T pop();
    boolean isEmpty();
    Iterator<T> iterator();  // Додавання методу iterator()
}

// Клас Stack, який реалізує інтерфейс DataStructure та має внутрішній клас для ітератора
class Stack<T> implements DataStructure<T> {
    private List<T> elements;

    // Конструктор класу
    public Stack() {
        elements = new ArrayList<>();
    }

    // Додає елемент до стеку
    public void push(T element) {
        elements.add(element);
    }

    // Видаляє та повертає верхній елемент стеку
    public T pop() {
        if (isEmpty()) {
            return null;
        }
        return elements.remove(elements.size() - 1);
    }

    // Перевіряє, чи стек порожній
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    // Реалізація методу iterator()
    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }

    // Внутрішній клас для ітератора
    private class StackIterator implements Iterator<T> {
        private int index = elements.size() - 1;

        @Override
        public boolean hasNext() {
            return index >= 0;
        }

        @Override
        public T next() {
            return elements.get(index--);
        }
    }
}

// Клас CalendarDate з реалізацією Comparable<>
class CalendarDate implements Comparable<CalendarDate> {
    private int year;
    private int month;
    private int day;

    // Конструктор класу
    public CalendarDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // Геттери для року, місяця та дня
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    // Реалізація методу toString()
    @Override
    public String toString() {
        return String.format("%04d/%02d/%02d", year, month, day);
    }

    // Реалізація методу compareTo() для порівняння дат
    @Override
    public int compareTo(CalendarDate otherDate) {
        if (year != otherDate.year) {
            return Integer.compare(year, otherDate.year);
        }
        if (month != otherDate.month) {
            return Integer.compare(month, otherDate.month);
        }
        return Integer.compare(day, otherDate.day);
    }
}

// Головний клас програми
public class Main {
    public static void main(String[] args) {
        Stack<CalendarDate> dateStack = new Stack<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Додати дату до стеку");
            System.out.println("2. Видалити дату зі стеку");
            System.out.println("3. Показати стек");
            System.out.println("4. Вийти");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Очищення буфера після nextInt()

            switch (choice) {
                case 1:
                    // Додавання дати до стеку
                    System.out.print("Введіть рік: ");
                    int year = scanner.nextInt();
                    System.out.print("Введіть місяць: ");
                    int month = scanner.nextInt();
                    System.out.print("Введіть день: ");
                    int day = scanner.nextInt();
                    dateStack.push(new CalendarDate(year, month, day));
                    break;
                case 2:
                    // Видалення дати зі стеку
                    CalendarDate removedDate = dateStack.pop();
                    if (removedDate != null) {
                        System.out.println("Видалено: " + removedDate);
                    } else {
                        System.out.println("Стек порожній, немає дат для видалення.");
                    }
                    break;
                case 3:
                    // Виведення стеку
                    System.out.println("Стек:");
                    printStack(dateStack);
                    break;
                case 4:
                    // Вихід з програми
                    System.out.println("Програма завершена.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Неправильний вибір. Будь ласка, введіть вірну опцію.");
            }
        }
    }

    // Метод для виведення елементів стеку без зміни самого стеку
    private static <T> void printStack(DataStructure<T> dataStructure) {
        for (T element : dataStructure) {
            System.out.println(element);
        }
    }
}
