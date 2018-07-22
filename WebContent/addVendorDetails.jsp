
<%@ include file="navbar.jsp" %>
		
		<div class="mainContainer" >
								
					 <div style=" margin: 0 auto;width:20%;">
			    						
			    						<form name="myform" action="saveVendorDetails.jsp" method="post" >
			    						<table id="organizationTable">
			    							<tr>
			    								<td colspan="2" style="text-align: center;">
			    								Vendor Details <br><br>
			    								</td>
			    							</tr>
			    							<tr>	
			    								<td>Vendor Name<br><br></td>
			    								<td><input type="text" name="vendorname" ><br><br>
			    							</tr>
			    							<tr>	
			    								<td>Vendor URL<br><br></td>
			    								<td><input type="text" name="vendorid" ><br><br>
			    							</tr>
			    							<tr>	
			    								<td>Auth Key <br><br></td>
			    								<td><input type="text" name="authkey" ><br><br>
			    							</tr>
			    							<tr>	
			    								<td>Route <br><br></td>
			    								<td><input type="text" name="route" placeholder="2-transactional,5-promotional"><br><br>
			    							</tr>
			    							<tr>	
			    								<td>Counry Code <br><br></td>
			    								<td><input type="text" name="countrycode" ><br><br>
			    							</tr>
			    							
			    							
			    							
			    							<tr>	
													<td colspan="8" style="text-align: center;">
														<input type="submit" value="SaveData" style="width: 100px;">
													</td>		
														
			    							</tr>
			    							
			    						</table>	
			    						</form>
			    		</div>
			    					
			    					
			
		
    </div>
	<%-- <%response.sendRedirect("SMSForm.jsp"); %> --%>	

	 
</body>

</html>