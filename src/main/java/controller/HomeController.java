package controller;

import model.Category;
import service.CategoryService;
import model.Product;
import service.InvoiceService;
import service.ProductService;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HomeController", value = "/home-page")
public class HomeController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("html/text; charset= UTF-8");
        //load 6 sản phẩm
        ArrayList<Product> listSixProduct = ProductService.getInstance().listSixProduct(0);
        request.setAttribute("listP", listSixProduct);
        //load danh mục
        ArrayList<Category> listCategory = CategoryService.getInstance().listCategory();
        request.setAttribute("listCate", listCategory);
        //load product mới nhất
        Product latestProduct = ProductService.getInstance().latestProduct();
        request.setAttribute("latestP", latestProduct);
        request.setAttribute("hiddenLoadmore", "block");
        //load màu sản phẩm
        ArrayList<String> listColorP = ProductService.getInstance().listColorP();
        request.setAttribute("listColorP", listColorP);
        //load sản phảm bán chạy nhất
        int idProduct = InvoiceService.getInstance().idBestSaler();
        Product bestProduct = null;
        if (idProduct != 0) {
            bestProduct = ProductService.getInstance().getProductById(idProduct);
        }
        request.setAttribute("bestSaler", bestProduct);
        //load carousel
        String source = getServletContext().getRealPath("/") + "image\\Carousel";
        File fileSource = new File(source);
        File[] filesNew = fileSource.listFiles();
        ArrayList<String> listCarousel = new ArrayList<>();
        for (File f : filesNew) {
            listCarousel.add(f.getName());
        }
        request.setAttribute("listCarousel", listCarousel);
        request.getRequestDispatcher("HomePage.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}
