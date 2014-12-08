package com.kalatag.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kalatag.domain.Attachment;
import com.kalatag.domain.Coupon;
import com.kalatag.domain.Deal;
import com.kalatag.service.AttachmentService;
import com.kalatag.service.CouponService;
import com.kalatag.service.DealService;

@Controller
@RequestMapping(value = "/files")
public class FilesController {

	@Autowired
	AttachmentService attachmentService;
	
	@Autowired
	DealService dealService;
	
	@Autowired
	CouponService couponService;
	
	@RequestMapping(value = "/attachments/{id}/dynamicImage", method = RequestMethod.GET)
    public String getAttachmentContent(@PathVariable("id") int id, @RequestParam("width") int width, @RequestParam("height") int height, HttpServletResponse response, Model model) {
       Attachment att = attachmentService.find(id);
        if (att != null) {
            byte[] imageInByte = att.getContent();
            if (imageInByte != null) {
                try {
                    response.setContentType(att.getContentType());
                    OutputStream out = response.getOutputStream();
                    ByteArrayInputStream ins = new ByteArrayInputStream(imageInByte);
                    BufferedImage scaledImg = Scalr.resize(ImageIO.read(ins), Scalr.Method.QUALITY, Scalr.Mode.FIT_EXACT, width, height);
                    ImageIO.write(scaledImg, "jpg", out);
                    out.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
	
	@RequestMapping(value = "/deals/{id}/thumbnail", method = RequestMethod.GET)
    public String getItemThumbnail(@PathVariable("id") int id, @RequestParam("width") int width, @RequestParam("height") int height, HttpServletResponse response, Model model) {
       Deal item = dealService.find(id);
        if (item != null) {
            byte[] imageInByte = item.getThumbnail();
            if (imageInByte != null) {
                try {
                    response.setContentType("image/jpeg");
                    OutputStream out = response.getOutputStream();
                    ByteArrayInputStream ins = new ByteArrayInputStream(imageInByte);
                    BufferedImage scaledImg = Scalr.resize(ImageIO.read(ins), Scalr.Method.QUALITY, Scalr.Mode.FIT_EXACT, width, height);
                    ImageIO.write(scaledImg, "jpg", out);
                    out.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
	
	@RequestMapping(value = "/coupons/{id}/qrcode", method = RequestMethod.GET)
    public String getCouponQrcode(@PathVariable("id") int id, @RequestParam("width") int width, @RequestParam("height") int height, HttpServletResponse response, Model model) {
      Coupon coupon = couponService.find(id);
        if (coupon != null) {
            byte[] imageInByte = coupon.getQrcode();
            if (imageInByte != null) {
                try {
                    response.setContentType("image/jpeg");
                    OutputStream out = response.getOutputStream();
                    ByteArrayInputStream ins = new ByteArrayInputStream(imageInByte);
                    BufferedImage scaledImg = Scalr.resize(ImageIO.read(ins), Scalr.Method.QUALITY, Scalr.Mode.FIT_EXACT, width, height);
                    ImageIO.write(scaledImg, "jpg", out);
                    out.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
	
	@RequestMapping(value = "/coupons/{id}/barcode", method = RequestMethod.GET)
    public String getCouponBarcode(@PathVariable("id") int id, @RequestParam("width") int width, @RequestParam("height") int height, HttpServletResponse response, Model model) {
      Coupon coupon = couponService.find(id);
        if (coupon != null) {
            byte[] imageInByte = coupon.getBarcode();
            if (imageInByte != null) {
                try {
                    response.setContentType("image/jpeg");
                    OutputStream out = response.getOutputStream();
                    ByteArrayInputStream ins = new ByteArrayInputStream(imageInByte);
                    BufferedImage scaledImg = Scalr.resize(ImageIO.read(ins), Scalr.Method.QUALITY, Scalr.Mode.FIT_EXACT, width, height);
                    ImageIO.write(scaledImg, "jpg", out);
                    out.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
	
}
