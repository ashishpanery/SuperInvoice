package com.glnetcomsolutions.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Servlet implementation class InvoiceMasterServlet
 */
public class InvoiceMasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvoiceMasterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (ClassNotFoundException | SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				doProcess(request, response);
			} catch (ClassNotFoundException | SQLException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {
		
		try{
			
			String requestType = request.getParameter("requestType");
			if("saveInvoice".equalsIgnoreCase(requestType)){
				String detail = request.getParameter("obj");
				JSONParser parser = new JSONParser();
				InvoiceMaster.saveInvoiceDetail((JSONObject)parser.parse(detail));
			}else if("getInvoiceHistroy".equals(requestType)){
				ItemDetail.writeJSONResponse(response, InvoiceMaster.getInvoiceHistory());
			}else if("invoiceitems".equals(requestType)){
				String invoiceId = request.getParameter("id");
				ItemDetail.writeJSONResponse(response,InvoiceMaster.getInvoiceItems(invoiceId));
			}else if("downloadExcelReport".equals(requestType)){
				Util.createExcelReport(response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
