$(function() {
	$("#camelopenshiftform").submit(
			function(event) {

				event.preventDefault();
				
				var messageContainer = $("#responseContainer");
				
				var url = "";
				var successFunction = "";
				var errorFunction = "";
				
				messageContainer.html("");
				
				
				// Perform Validation
				var openshiftusername = $("#openshiftusername").val();
				var openshiftpassword = $("#openshiftpassword").val();
				
				
				if(openshiftusername =="" || openshiftpassword == "") {
					messageContainer.html("User name and password is required");
					return;
				}
				
				// Configure Request
				if($('input[name=operation]:checked', '#camelopenshiftform').val() == "applicationstatus") {
					url = "/rest/applicationsstartup/appstatus";
					
					successFunction = function(data) {
						$(data).find("application").each(function() {
				        	messageContainer.append("Name: "+$(this).find("name").text() + "<br/>");
				        	messageContainer.append("Initial State: "+$(this).find("initialState").text() + "<br/>");
				        	messageContainer.append("Current State: "+$(this).find("currentState").text() + "<br/><br/>");

				        });
					};
					
						
				}
				else {
					
					var opsaction = $('input[name=opsaction]:checked', '#camelopenshiftform');
					
					if(typeof opsaction.val() == "undefined") {
						messageContainer.html("Operation option is required");
						return;
					}
					
					
					// Display User
					if(opsaction.val() == 'user') {
						url = "/rest/operation/user";
						successFunction = function(data) {
					        messageContainer.append("User Name: "+$(data).find("userName").text() + "<br/>");
					        messageContainer.append("Server: "+$(data).find("server").text() + "<br/>");
					        messageContainer.append("Consumed Gears: "+$(data).find("consumedGears").text() + "<br/>");
					        messageContainer.append("Max Gears: "+$(data).find("maxGears").text() + "<br/><br/>");
						};
					}
					
					// Display Domain
					else if (opsaction.val() == 'domains') {
						url = "/rest/operation/domains";
						successFunction = function(data) {
					        messageContainer.append("Id: "+$(data).find("id").text() + "<br/>");
					        messageContainer.append("Suffix: "+$(data).find("suffix").text() + "<br/><br/>");
						};
					
					}
					
					// Display Applications in Domain
					else if (opsaction.val() == 'applications') {
						
						var domainId = $("#applicationdomain").val();
						
						if(domainId == '') {
							messageContainer.html("Domain ID required");
							return;
						}
						
						url = "/rest/operation/applications/"+domainId;
						successFunction = function(data) {
							$(data).find("application").each(function() {
						        messageContainer.append("Name: "+$(this).find("name").text() + "<br/>");
						        messageContainer.append("UUID: "+$(this).find("uuid").text() + "<br/>");
						        messageContainer.append("Application URL: <a href='"+$(this).find("applicationUrl").text() + "'>"+$(this).find("applicationUrl").text()+"</a><br/>");
						        messageContainer.append("Git Url: <a href='"+$(this).find("gitUrl").text() + "'>"+$(this).find("gitUrl").text()+"</a><br/>");
						        messageContainer.append("SSH Url: <a href='"+$(this).find("sshUrl").text() + "'>"+$(this).find("sshUrl").text()+"</a><br/><br/>");
							});
						};
						
						errorFunction = function(xhr,txtStat,errorThrown) {
							if(xhr.status == 404) {
								messageContainer.html("Domain does not exist");
							}
						};
					
					}
					
					else {
						messageContainer.html("Valid Operation Option Not Selected");
						return;
					}
					
					
					
				}

				
				doCall(openshiftusername, openshiftpassword,url,successFunction, errorFunction);
				

				
				
			});
	
	$("input[name=operation]:radio").change(function () {
		
		var opsDiv = $("#opsDiv");
		
		if ($("#applicationstatus").is(":checked")) {
            opsDiv.hide();
            $('input[name=opsaction]').removeAttr('checked');
        }
        else {
            opsDiv.show();
            $("#applicationdomain").val("");
        }
		
       
    })
    
    $("input[name=opsaction]:radio").change(function () {
		
		var applicationsDomainDiv = $("#applicationsDomainDiv");
		
		if ($("#openshiftapplications").is(":checked")) {
			applicationsDomainDiv.show();
			}
        else {
        	applicationsDomainDiv.hide();
            $("#applicationdomain").val("");
        }
		
       
    })
    
    function doCall(openshiftusername, openshiftpassword, url, successFunction, errorFunction) {
		$('input[type="submit"]').attr('disabled','disabled');
		var messageContainer = $("#responseContainer");
		messageContainer.html("");


		$.ajax({
			type: 'GET',
			beforeSend: function(xhr) { 
				xhr.setRequestHeader("OpenShiftUserName",openshiftusername);
				xhr.setRequestHeader("OpenShiftPassword",openshiftpassword);
			},
			url: url,
			success: function(data) { 

				$('input[type="submit"]').removeAttr('disabled');
				
				if(successFunction) successFunction(data);

			},
			error: function(xhr, txtStat, errThrown) { 
				if(xhr.status == 401) {
					messageContainer.html("Invalid Username and Password");
				}
				else {
					messageContainer.html("An Unknown Error Occurred");
				}
				
		        $('input[type="submit"]').removeAttr('disabled');
		        
		        if(errorFunction) errorFunction(xhr,txtStat,errThrown);
			},
			dataType: "xml",
		});
	}
    
    
});