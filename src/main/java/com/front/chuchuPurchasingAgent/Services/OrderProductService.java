package com.front.chuchuPurchasingAgent.Services;

import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.front.chuchuPurchasingAgent.ModelBuilders.OrderProductBuilder;
import com.front.chuchuPurchasingAgent.Models.MyOrder;
import com.front.chuchuPurchasingAgent.Models.OrderProduct;
import com.front.chuchuPurchasingAgent.Repositories.OrderProductRepository;
import com.front.chuchuPurchasingAgent.tool.util.Id;

@Service
public class OrderProductService {
	@Autowired
	private OrderProductRepository orderProductRepository;
	/**
	 * 寫入設定
	 * @return
	 * @throws Exception 
	 */
	public OrderProduct insertSetting(OrderProduct product, String orderId, String memberId) throws Exception {
		Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
		 return new OrderProductBuilder(product)
				 .buildId(Id.id32())
				 .buildOrderId(orderId)
				 .buildCreateTime(now)
				 .buildCreator(memberId)
				 .buildModifyTime(now)
				 .buildModifier(memberId)
				 .build();
	}
	
	public void saveList(String memberId, MyOrder myOrder, List<OrderProduct> products) throws Exception {
		for(OrderProduct product : products) {
			if(product.getName() == null) continue;
			this.insertSetting(product, myOrder.getId(), memberId);
			orderProductRepository.save(product);
		}
	}
	/**
	 * 取得列表
	 * @param orderProduct
	 * @return
	 */
	public List<OrderProduct> findAll(OrderProduct orderProduct){
		Example<OrderProduct> example =  Example.of(orderProduct);
		return orderProductRepository.findAll(example);
	}
}
