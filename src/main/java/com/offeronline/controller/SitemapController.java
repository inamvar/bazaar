package com.offeronline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.offeronline.domain.Deal;
import com.offeronline.domain.ItemStatus;
import com.offeronline.service.DealService;
import com.offeronline.util.XmlUrl;
import com.offeronline.util.XmlUrlSet;

@Controller
public class SitemapController {

	@Autowired
	DealService dealService;

	@RequestMapping(value = "/sitemap.xml", method = RequestMethod.GET)
	@ResponseBody
	public XmlUrlSet main() {
		XmlUrlSet xmlUrlSet = new XmlUrlSet();
		create(xmlUrlSet, "", XmlUrl.Priority.HIGH);

		create(xmlUrlSet, "/login", XmlUrl.Priority.MEDIUM);
		create(xmlUrlSet, "/register", XmlUrl.Priority.MEDIUM);

		List<Deal> deals = dealService.findDealsByStatus(ItemStatus.ON);
		if (deals != null && deals.size() > 0) {
			for (Deal deal : deals) {
				create(xmlUrlSet, "/detail?deal=" + deal.getId(), XmlUrl.Priority.HIGH);
			}
		}
		return xmlUrlSet;
	}

	private void create(XmlUrlSet xmlUrlSet, String link,
			XmlUrl.Priority priority) {
		xmlUrlSet.addUrl(new XmlUrl("http://www.offeronline.com" + link, priority));
	}

}
