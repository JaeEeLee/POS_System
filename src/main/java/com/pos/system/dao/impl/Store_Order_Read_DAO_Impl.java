package com.pos.system.dao.impl;

import com.pos.system.dao.Store_Order_Read_DAO;
import com.pos.system.dto.Store_Order_DTO;
import com.pos.system.util.SqlSessionTemplate_Comm;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Store_Order_Read_DAO_Impl extends SqlSessionTemplate_Comm implements Store_Order_Read_DAO {

	@Override
	public List<Store_Order_DTO> orderListAll(Store_Order_DTO dto) {
		return session.selectList("mybatis.mapper.Store_Order.orderList", dto);
	}
	
}
