/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.practica02.dao;

import com.practica02.domain.Arbol;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Maria
 */
public interface ArbolDao extends JpaRepository<Arbol,Long> {

    public List<Arbol> findAllActive();
    
}
