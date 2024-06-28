/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gui;

/**
 * Интерфейс для наблюдателя данных.
 *
 * @author Kate Shcherbinina
 * @version 1.0
 * @param <T> Тип данных, за которыми наблюдает данный наблюдатель.
 */
public interface DataObserver<T extends Object> {

    /**
     * Метод, который будет вызван при обновлении данных.
     * 
     * @param object Объект данных, который обновился.
     */
    void update(T object);

}
