package com.front.chuchuPurchasingAgent.Services;

import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.front.chuchuPurchasingAgent.Constants;
import com.front.chuchuPurchasingAgent.Inputs.FinallyInputs;
import com.front.chuchuPurchasingAgent.ModelBuilders.QeryProductBuilder;
import com.front.chuchuPurchasingAgent.Models.Query;
import com.front.chuchuPurchasingAgent.Models.QueryProduct;
import com.front.chuchuPurchasingAgent.Repositories.QueryProductRepository;
import com.front.chuchuPurchasingAgent.tool.util.Id;

@Service
public class QueryProductSerivce {
	@Autowired
	private QueryProductRepository queryProductRepository;
	@Autowired
	private FinallyRateService finallyRateService;
	
	/**
	 * 寫入設定
	 * @return
	 * @throws Exception 
	 */
	public QueryProduct insertSetting(QueryProduct product, String queryId,String queryEmail) throws Exception {
		Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
		 return new QeryProductBuilder(product)
				 .buildId(Id.id32())
				 .buildQueryId(queryId)
				 .buildCreateTime(now)
				 .buildCreator(queryEmail)
				 .buildModifyTime(now)
				 .buildModifier(queryEmail)
				 .build();
	}
	
	public void saveList(Query query, List<QueryProduct> products) throws Exception {
		for(QueryProduct product : products) {
			if(product.getName() == null) continue;
			this.insertSetting(product, query.getId(), query.getEmail());
			queryProductRepository.save(product);
		}
	}
	/**
	 * 取得列表
	 * @param product
	 * @return
	 */
	public List<QueryProduct> findAll(QueryProduct product) {
		Example<QueryProduct> example = Example.of(product); 
		return queryProductRepository.findAll(example);	
	}
	/**
	 * 設定匯率及小計
	 * @param query
	 * @param products
	 * @throws Exception
	 */
	public void setProudctRateAndTotal(Query query, List<QueryProduct> products) throws Exception {
		double rate = finallyRateService.getRate(this.getFinallyInputs(query, products));
		for(QueryProduct product: products) {			
			int total = this.getTotal(rate, product);
			new QeryProductBuilder(product)
			.buildRate(rate)
			.buildTotal(total);
		}
	}
	
	/**
	 * 取得商品原幣總價
	 * @param products
	 * @return
	 */
	public int getTotalPrice(List<QueryProduct> products) {
		int result = 0;
		for(QueryProduct product : products) {
			result += product.getPrice() * product.getCount();
		}
		return result;
	}
	/**
	 * 取得商品合計
	 * @param products
	 * @return
	 */
	public int getAllTotalPrice(List<QueryProduct> products) {
		//匯率都一樣 
		double rate = products.get(0).getRate();
		int totalPrice = this.getTotalPrice(products);
		int allTotalPrice = (int) Math.ceil(totalPrice * rate);
		return allTotalPrice;
	}
	
	//設定FinallyInputs
		private FinallyInputs getFinallyInputs(Query query, List<QueryProduct> products) {
			FinallyInputs inputs = new FinallyInputs();
			inputs.setCode(query.getDiscountCode());
			inputs.setCountryId(query.getCountryId());
			inputs.setEmail(query.getEmail());
			inputs.setProducts(products);
			//TODO 取得大戶金額標準
			inputs.setPrice(Constants.VIP_STANDARD);
			inputs.setCurrencyId(query.getCurrencyId());
			return inputs;
		}
	
	private int getTotal(double rate, QueryProduct product) {
		return (int) Math.ceil(product.getPrice() * product.getCount() * rate);
	}
}
