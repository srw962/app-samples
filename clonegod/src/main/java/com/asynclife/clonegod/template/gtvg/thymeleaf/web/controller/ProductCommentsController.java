package com.asynclife.clonegod.template.gtvg.thymeleaf.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.asynclife.clonegod.template.gtvg.thymeleaf.business.entities.Product;
import com.asynclife.clonegod.template.gtvg.thymeleaf.business.services.ProductService;

@Controller
@RequestMapping("/gtvg/")
public class ProductCommentsController {
	
	@RequestMapping("/product/comments")
    public ModelAndView process(ModelAndView mav, @RequestParam Integer prodId) {
        
        //final Integer prodId = Integer.valueOf(request.getParameter("prodId"));
        
        final ProductService productService = new ProductService();
        final Product product = productService.findById(prodId);
        
        mav.addObject("prod", product);
        mav.setViewName("product/comments");
        
        return mav;
    }

}
