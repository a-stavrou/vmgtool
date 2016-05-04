<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<!-- Button trigger modal -->
<button class="add-button-modal btn btn-primary btn-lg" data-toggle="modal"
	data-target="#myModal">Add Question</button>

 <script> var loadFile = function(event) { 
	 				
	 				var reader = new FileReader();
				    reader.onload = function(){ var output = document.getElementById('imageUpload');
				    output.src = reader.result; };
				    reader.readAsDataURL(event.target.files[0]); }; 				    
</script>
					
					
<script type="text/javascript">

	$(document)
			.ready(
					function() {
						
						$("input[type=file]").change(function(event){
							var edit = event.target.id
							if(edit == 'imageUpload'){
								
						        var reader = new FileReader();
							    reader.onload = function(){ 
							    var output = document.getElementById('output');
							    output.src = reader.result; 
							    };
							    reader.readAsDataURL(event.target.files[0]);	
							    //$("#output").width(400); 
							   // $("#output").height(360);
							}else{

						        var reader = new FileReader();
							    reader.onload = function(){ 
							    var output = document.getElementById('outputinsert');
							    output.src = reader.result; 
							    };
							    reader.readAsDataURL(event.target.files[0]);	
							  //  $("#outputinsert").width(400); 
							  //  $("#outputinsert").height(360);
							}
					    });
	
						
						var max_fields = 10; 
						var wrapper = $(".input_fields_wrap"); 
						
						var add_button = $(".add_field_button"); 
						var add_rule_button = $(".add_rule_button");
						var rule_wrapper = $(".input_fields_wrap_option3"); 
						var rule_update_wrapper = $(".input_fields_update_wrap_option3"); 

						var x = 0;
						$(add_button)
								.click(
										function(e) {
											
											var edit = e.target.id
											
											if(edit =='edit'){
												wrapper = $('.update_input_fields_wrap');
											}
											
											$(wrapper).empty();
											$(rule_wrapper).empty();
											$(rule_update_wrapper).empty();
			
											var fieldCount = $('#numberOfOptions').prop('value');
											if(fieldCount == ''){
												fieldCount = $('#numberOfOptionsUpdate').prop('value');
											}
											
										
											var selectedVal = "";
											var selected = $("input[type='radio'][name='optionsRadios']:checked");
											if (selected.length > 0) {
											    selectedVal = selected.val();
											}
											var selection = ($('#optionsRadios12').prop('checked'));
											if(selection == true){
												for (var i = 0; i < fieldCount; i++) {
													var inputField = '<div class="form-group"> <label for="fields" class="col-sm-2 control-label">Option '+i+'</label>  <div class="col-sm-8"> <form:input id="insertteste" path="question.questionOptions[0].name" cssClass="triggerAlert form-control"  name="mxt[]"/></div></div>'

														$(wrapper) 
																.append(inputField.replace("questionOptions[0]","questionOptions["+i+"]").replace("insertteste",('insertteste'+i)).replace("xx1",i));
													
													$(('#insertteste'+i)).mask('9');
													
													$(('#checkbox')+i).on('change', function(e) {

														var count = 0 ;
												
														$('.triggerAlert').each(function(){
															if(this.value == ''){
																count++;
															}
														});
														
														if(count > 0){
														
															this.checked = false;
															$('.alert').show();
														}else {
														
															$('.triggerAnswer').attr('checked', false);
															this.checked = true;
														}
														
														
													});		
												}
											        	
									        	
											}else{
												for (var i = 0; i < fieldCount; i++) {
													
													var inputField = '<div class="form-group"> <label for="fields" class="col-sm-2 control-label">Option '+i+'</label> <div class="col-sm-10"> <form:input path="question.questionOptions[0].name" cssClass="form-control"  name="mytext[]"/></div></div>'

														$(wrapper) 
																.append(inputField.replace("questionOptions[0]","questionOptions["+i+"]"));
												}
											}

			
										});
						
						$(add_rule_button)
						.click(
								function(e) { 
									e.preventDefault();
						
									$(wrapper).empty();
									$(rule_wrapper).empty();
									$(rule_update_wrapper).empty();

								
									var fieldCount = $('#numberofrules').prop('value');
									if(fieldCount == ''){
										fieldCount = $('#numberofrulesupdate').prop('value');
										rule_wrapper = rule_update_wrapper;
									}
								
									if(fieldCount == ''){
										fieldCount = 0;
									}
							
									for (var i = 0; i < fieldCount; i++) {
										var inputField = '<div class="form-group"> <label for="fields" class="col-sm-2 control-label">Rule '+i+'</label> <div class="col-sm-10"> <form:input path="question.questionOptions[0].name" cssClass="form-control"  name="mytext[]"/></div></div>'

										$(rule_wrapper)
											.append(inputField.replace("questionOptions[0]","questionOptions["+i+"]"));
								
									}
								});
						
						$('.optionsRadios').change(function() {
					        if (this.value == 1) {
					        	$(".div_option_3").hide();
					            $(".div_option_1_2").show();
					        }
					        else if (this.value == 2) {
					        	$(".div_option_3").hide();
					        	 $(".div_option_1_2").show();
					        }
					        else if (this.value == 3) {
					        	$(".div_option_3").hide();
					        	 $(".div_option_1_2").show();
					        }
					        else if (this.value == 4) {
					        	$(".div_option_3").hide();
					        	 $(".div_option_1_2").show();
					        }
					       
					    });
						
						
						$('.previewButton').on('click', function() {
							var id = $(this).attr('data-id');
						
						});
						
						$('.add-button-modal').on('click', function() {
						
							$(wrapper).empty();
							$(rule_wrapper).empty();
							$('.alert').hide();

						    document.getElementById("questionform").reset();
						    document.getElementById("updateQuestionForm").reset();
						});


						$('.editButton').on('click', function() {
					  
					        var id = $(this).attr('data-id');
							$(wrapper).empty();
							$(rule_wrapper).empty();
							var url = window.location.href +'questions/' + id ;

				        $.ajax({
				            url: url,
				            method: 'GET'
				        }).success(function(response) {
						    document.getElementById("updateQuestionForm").reset();
				            
						    $("#output").attr("src", "data:image/png;base64,"+response.base64Image);
						    $("#output").width(400); 
						    $("#output").height(360);
						    
						    
				            $('#updateQuestionForm')
				                .find('[name="title"]').val(response.title).end()
				                .find('[name="updatequestionid"]').val(response.id).end()
				                .find('[name="description"]').val(response.description).end();
				            
				            
				            $('#selectedOptionNumberUpdate').val(response.answer.selectedOptionNumber)
				            
				        	$(".updatequestionid").val(response.id);
							
				            if(response.type == 4){
					        	$("#optionsRadios4").prop("checked", true)

						        	for (var i = 0; i < options.length; i++) {
						        		var option = options[i];
										var inputField = '<div class="form-group"> <label for="fields" class="col-sm-2 control-label">Option '+i+'</label> <div class="col-sm-10"> <form:input id="teste" path="question.questionOptions[0].name" value="valueplaceholder" cssClass="questionoption form-control"  name="mytext[]"/></div></div>'
										$('.update_input_fields_wrap') 
												.append(inputField.replace("questionOptions[0]","questionOptions["+i+"]").replace("valueplaceholder",option.name));
										
						          }
					        	
					        	
				            }else {	            	
					        	
					        	$(".div_option_3").hide();
					        	$(".div_option_1_2").show();
					        	 $('#updateQuestionForm')
					                .find('[name="numberOfOptions"]').val(response.numberOfOptions).end();
		
					        	var options = response.questionOptions;
				            	if (response.type == 2){
				            		
						        	for (var i = 0; i < options.length; i++) {
						        		var option = options[i];
										var inputField = '<div class="form-group"> <label for="fields" class="col-sm-2 control-label">Option '+i+'</label> <div class="col-sm-10"> <form:input id="teste" path="question.questionOptions[0].name" value="valueplaceholder" cssClass="questionoption form-control"  name="mytext[]"/></div></div>'
										$('.update_input_fields_wrap') 
												.append(inputField.replace("questionOptions[0]","questionOptions["+i+"]").replace("valueplaceholder",option.name).replace("teste",('teste'+i)));
										$(('#teste'+i)).mask('9');
										
						          }
						        	$("#optionsRadios2").prop("checked", true);
				            	}else{
						        	for (var i = 0; i < options.length; i++) {
						        		var option = options[i];
										var inputField = '<div class="form-group"> <label for="fields" class="col-sm-2 control-label">Option '+i+'</label> <div class="col-sm-10"> <form:input id="teste" path="question.questionOptions[0].name" value="valueplaceholder" cssClass="questionoption form-control"  name="mytext[]"/></div></div>'
										$('.update_input_fields_wrap') 
												.append(inputField.replace("questionOptions[0]","questionOptions["+i+"]").replace("valueplaceholder",option.name));
										
						          }
						        	if (response.type == 1){
						        		$("#optionsRadios1").prop("checked", true);
						        	}else {
						        		$("#optionsRadios3").prop("checked", true);
						        	}
				            		
				            }

				        }});
				    });

				})
						 	
</script>


<form:form commandName="question" id="updateQuestionForm" cssClass="updateQuestionForm form-horizontal"
	action="editquestions" method="POST" enctype="multipart/form-data">
	<!-- Modal -->
	<div class="modal fade" id="myModalEdit" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="btn-edit-close close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Add New Question</h4>
				</div>
				<div class="modal-body">
				
					<form:hidden path="id" name="updatequestionid" value="" cssClass="updatequestionid form-control" />
				

                    <div class="form-group">
						<label for="exampleInputFile" class="col-sm-2 control-label">Image</label>
						<form:input type="file" path="imageUpload" id="imageUpload" class="imageUpload" accept="image/*" />
					</div>
					<img src="data:image/png;base64,${question.base64Image}" id="output" name="updateimage" class="output" alt="" class="img-rounded">
					
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">Question:</label>
						<div class="col-sm-10">
							<form:input path="title" name="title" cssClass="form-control" />
						</div>
					</div>
					
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">Description:</label>
						<div class="col-sm-10">
							<form:input path="description" name="description" cssClass="form-control" />
						</div>
					</div>
					
					<div class="radio">
						<label> <form:radiobutton class="optionsRadios" path="type"  name="optionsRadiosUpdate"
							id="optionsRadios1" value="1" checked="true"/> Text On Button
						</label>
					</div>

					<div class="radio">
						<label> <form:radiobutton class="optionsRadios" path="type" name="optionsRadiosUpdate"
							id="optionsRadios2" value="2" onclick=""/>  Numbers on Button(With Text Field)
						</label>
					</div>
					
					<div class="radio">
						<label> <form:radiobutton class="optionsRadios" path="type" name="optionsRadiosUpdate"
							id="optionsRadios3" value="3"/> Radio Option
						</label>
					</div>
					
					<div class="radio">
						<label> <form:radiobutton class="optionsRadios" path="type" name="optionsRadiosUpdate"
							id="optionsRadios4" value="4"/> Checkbox
						</label>
					</div>
					
					<br /> <br />
					<div class="div_option_1_2">
						<div class="form-group">
							<div>
								
							</div>
							<div>
								
								<br /><br />
								
								<div class="form-group">
									<div>
										<label for="name" class="col-sm-4 control-label">number of options:</label>
										<div class="col-sm-2">
											<form:input id="numberOfOptionsUpdate" name="numberOfOptions" path="numberOfOptions" cssClass="numberOfOptions form-control" />
										</div>
									</div>
									<div>
	
									 	<a id="edit"  class="add_field_button btn btn-default">Create Options</a>
										
									</div>
								</div>
								
								 <div class="form-group">
										<label for="name" class="col-sm-4 control-label">Correct Answer:</label>
										<div class="col-sm-2">
											<form:input id="selectedOptionNumberUpdate" name="selectedOptionNumberUpdate" path="answer.selectedOptionNumber" cssClass="selectedOptionNumberUpdate form-control" />
										</div>
									</div>
									
								
										<div class="alert alert-danger" role="alert" hidden="true">
										  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
										  <span class="sr-only">Error:</span>
											please fill all options before choose the correct answer
										</div>
													
						
								<div class="update_input_fields_wrap"></div>
							</div>
						</div>
					</div>
				
					<div class="div_option_3" hidden="true">

					</div>
					
					<div class="div_option_4" hidden="true">

						
					</div>


				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<input type="submit" class="btn btn-primary" value="Save" />
				</div>
			</div>
		</div>
	</div>
</form:form>


<form:form  commandName="question" id="questionform" cssClass="questionform form-horizontal"
	action="questions" method="POST" enctype="multipart/form-data">
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Add New Question</h4>
				</div>
				<div class="modal-body">

					<form:hidden path="id" name="questionid" value="" cssClass="form-control" />
                    
                    <div class="form-group">
						<label for="exampleInputFile" class="inputimage col-sm-2 control-label">Image</label>
       				    <form:input type="file" path="imageUpload" name="updateimage" id="imageUploadUpdate" accept="image/*" />
					</div>
                    <div class="form-group"><img id="outputinsert"/> </div>
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">Question:</label>
						<div class="col-sm-10">
							<form:input path="title" name="title" cssClass="newtitle form-control" required="true"/>
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">Description:</label>
						<div class="col-sm-10">
							<form:input path="description" name="description" cssClass="form-control" required="true"/>
						</div>
					</div>
					
                    <h2>Interactions to Select Answer:</h2>
					<div class="radio">
						<label> <form:radiobutton class="optionsRadios" path="type"  name="optionsRadios"
							id="optionsRadios11" value="1" checked="true"/> Text On Button
						</label>
					</div>

					<div class="radio">
						<label> <form:radiobutton class="optionsRadios" path="type" name="optionsRadios"
							id="optionsRadios12" value="2" onclick=""/> Numbers on Button(With Text Field)
						</label>
					</div>
					<div class="radio">
						<label> <form:radiobutton class="optionsRadios" path="type" name="optionsRadios"
							id="optionsRadios22" value="3"/> Radio Option
						</label>
					</div>
					
					<div class="radio">
						<label> <form:radiobutton class="optionsRadios" path="type" name="optionsRadios"
							id="optionsRadios22" value="4"/> Checkbox
						</label>
					</div>

					<br /> <br />
					
	 				<div class="form-group">
						<label for="name" class="col-sm-4 control-label">Correct Answer:</label>
						<div class="col-sm-2">
							<form:input id="selectedOptionNumber" name="selectedOptionNumber" path="answer.selectedOptionNumber" cssClass="selectedOptionNumber form-control" />
						</div>
					</div>
						
						
					<div class="div_option_1_2">
						<div class="form-group">
							<div>
							</div>
							<div>
								
								<div class="form-group">
									<div>
										<label for="name" class="col-sm-4 control-label">number of options:</label>
										<div class="col-sm-2">
											<form:input id="numberOfOptions" name="numberOfOptions" path="numberOfOptions" cssClass="numberOfOptions form-control" />
										</div>
									</div>
									<div>
										
										<a id="insert"  class="add_field_button btn btn-default">Create Options</a>
										
									</div>
								</div>
									
						
								<div class="alert alert-danger" role="alert" hidden="true">
								  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
								  <span class="sr-only">Error:</span>
											please fill all options before choose the correct answer
								</div>
										
								<div class="input_fields_wrap"></div>
							</div>
						</div>
					</div>
					

				
					<div class="div_option_3" hidden="true">
			
					 </div>
					 
					 
					<div class="div_option_4" hidden="true">					
	
					</div>


				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<input type="submit" class="btn btn-primary" value="Save" />
				</div>
			</div>
		</div>
	</div>
</form:form>

<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>Title</th>
			<th>Description</th>
			<th>Actions</th>

		</tr>
	</thead>
	<tbody>
		<c:forEach items="${studyQuestions}" var="studyQuestion">
			<tr>

				<td>${studyQuestion.title}</td>
				<td>${studyQuestion.description}</td>
				<td> 				
					<a href="<spring:url value="questions/${studyQuestion.id}/preview" /> "
						class="btn btn-info">preview</a>
					
					<button class="editButton btn btn-success" data-id="${studyQuestion.id}" data-toggle="modal"
							data-target="#myModalEdit">Edit</button>
					 <a href="<spring:url value="questions/remove/${studyQuestion.id}" /> "
					class="btn btn-danger">remove</a></td>

			</tr>
		</c:forEach>
	</tbody>
</table>
