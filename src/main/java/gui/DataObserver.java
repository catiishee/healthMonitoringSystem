/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gui;


/**
 *
 * @author user
 * @param <T>
 */
public interface DataObserver<T extends Object>{
    void update(T object);
    
}
