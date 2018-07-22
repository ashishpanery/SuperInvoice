
<%@ include file="navbar.jsp" %>
		
		<div class="mainContainer" >
			
			
			  <div>
		
                  <p>To add vendor details please<a href="addVendorDetails.jsp">Click Here</a></p>
				
		    	</div>
			
			
					 <div style=" margin: 0 auto;width:20%;">
			    						
			    						<form name="myform" action="" method="post"  enctype="multipart/form-data"  onSubmit="return validateForm()">
			    					
			    						<table class="spacing-table" id="organizationTable">
			    									<br><br><br>  
			    										
						    				<tr>
						    				<td	id="show" style = "align :center;font-weight:bold;">	
						    				<br><br>												
													</td>
						    				</tr>
						    				<tr>
			    							    <td><textarea rows="2" cols="40"  placeholder="Vendor Name" name="vendorname" id="vendorname"></textarea></td>	
			    							</tr>
						    				
						    				<tr>
			    							    <td><textarea rows="2" cols="40"  placeholder="Sender Id" name="SenderId" id="SenderId"></textarea></td>	
			    							</tr>
			    									    							
						    				<tr>	
			    								
			    	    					<td><textarea rows="6" cols="40" name="phonenumber" id="phonenumber" placeholder="Enter mobile numbers here.Multiple mobiles numbers separated by comma"></textarea>
			    								</td>
			    							</tr>
			    							<tr>	
			    								
			    								<td><textarea rows="6" cols="40" id="msg" name="msg" placeholder="Write Message"></textarea>
			    							</tr>
			    						
			    							<tr>	
													<td colspan="2" style="text-align: left;">
														<input type="submit" value="Send SMS" id="sendmsg" style="width: 100px;">
													</td>
														
			    							</tr>
			    							
			    						</table>	
			    						
				
			    						</form>
			    		</div>
			    					
			    		<div id="response"></div>			
			
		
    </div>
			
		

	 
</body>

<script src="js/SMS.js"></script>
</html>