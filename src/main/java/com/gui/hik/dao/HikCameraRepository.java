package com.gui.hik.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gui.hik.bean.HikCamera;
@Repository
public interface HikCameraRepository extends JpaRepository<HikCamera,String> {

}
