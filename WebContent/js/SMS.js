$(document).ready(function(){

	
	
	$.ajax({
		url: "sendsms?",
	
		type: "Get",
     	success: function (data) {
     		 $("#show").html(data);	
        	   
        	     },
        error:  function (xhr, ajaxOptions, thrownError) {
        	alert("Fail");
         }
	       });
			
	
		 	 $("#sendmsg").click(function () {  
		 		 debugger
		 		var vendorName = $("#vendorname").val();
		 
	 		 var phonenumber = $("#phonenumber").val();
	 		  var sms = $("#msg").val();
		    var sID = $("#SenderId").val();
		
		
		 	$.ajax({
				url: "sendsms?phonenumber="+phonenumber+"&vendorName="+vendorName+"&sms="+sms+"&SenderId="+sID,
				type: "Post",
		     	
				success: function (data) {
		        	$("#response").html(data);
		     					        	    	
		        	     },
		        error:  function (xhr, ajaxOptions, thrownError) {
		        	alert("Fail");
		         }
			       });
		 
		 
	 	 });
});

		 	
			
			
		
