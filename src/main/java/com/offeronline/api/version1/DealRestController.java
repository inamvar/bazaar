package com.offeronline.api.version1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.offeronline.domain.Deal;
import com.offeronline.service.DealService;

@RestController
@RequestMapping("/api/v1")
public class DealRestController {

	@Autowired
	private DealService dealService;

	@RequestMapping(value = "/deals/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Deal> getDeals() {
		List<Deal> deals = new ArrayList<Deal>();
		try {

			
			deals = dealService.findAll();
			for (Deal d : deals) {
				d.setImages(null);
				d.setThumbnail(null);
			}
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		return deals;
	}
	
	@RequestMapping(value = "/deals/find/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Deal getDeal(@PathVariable("id") Integer id) {
		Deal deal = dealService.find(id);
		
		return deal;
	}


}
