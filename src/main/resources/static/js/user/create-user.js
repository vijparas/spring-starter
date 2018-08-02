function createUserView(){
	$("#modal").modal("show");
	
		$.ajax({
		url : user.create,
		
		method : 'GET',
		success : function(data) {
			
			$("#modal-body").html(data);
//			$("#roip_party_row_" + partyId).remove();

		},
		error : function(data) {
			swal("error", "Something went wrong", "error");
		},
		complete : function() {
			
		}
	});
}