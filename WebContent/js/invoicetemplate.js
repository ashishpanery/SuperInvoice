function saveContent(){
	
	var part = $('#part').val();
	var content = CKEDITOR.instances.editor.getData();
	console.log(content);
	 $.ajax({
		  url: "invoicetemplate.do?",
		  method: "POST",
		  data: { part : part, content:content, operation :"save" },
		  dataType: "html",
		  success:function(response){
			  alert("Template updated successfully");
		  }
		});
	
	
}

function getContent(){
	

		 $.ajax({
		url : "invoicetemplate.do?operation=get",
		method : "GET",
		datatype : "Json",
		success : function(response) {
			var part = $('#part').val();
			var content = response[part];
			CKEDITOR.instances.editor.setData(content);
		},
		error : function() {
		}
	});
}