<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<script type="text/javascript">


	$(document)
	.ready(
			function() {
				var wrapper = $(".wrapper"); 
				
				$('.createfieldbutton').on('click', function() {
					$(wrapper).empty();
					var fieldCount = $(this).attr('data-id');
					for (var i = 0; i < fieldCount; i++) {
						var inputField = '<form:input path="answer.answerOptions[0].name"  cssClass="anwser-fields btn-block form-control"  name="mytext[]"/><br/>'

							$(wrapper) 
									.append(inputField.replace("answerOptions[0]","answerOptions["+i+"]"));
					}
					var continueButton = '<input type="submit" class="btn btn-default" value="Continue" />'
				
						$(wrapper)
							.append(continueButton);

					$(".anwser-fields").val("");
	
				});
				
				$('.createfieldbuttonType1').on('click', function() {

					var continueButton = '<input type="submit" class="btn btn-default" value="Continue" />'
						$('.wrappertype1')
							.append(continueButton);
				});
				
				$('.triggerOption1').click(function(e){
					$('.selectedOptionValue').val(this.value)
				});
			});
								 	
</script>

<form:form commandName="answer" modelAttribute="answer" action="next" cssClass="form-horizontal">
	
			<form:hidden path="selectedOptionValue" id="selectedOptionValue" name="optionValue" value="${answer.selectedOptionValue}" cssClass="selectedOptionValue form-control" />
			<form:hidden path="selectedPremise" id="selectedPremise" name="selectedPremise" value="${answer.selectedPremise}" cssClass="selectedPremise form-control" />
			<form:hidden path="selectedConclusion" id="selectedConclusion" name="selectedConclusion" value="${answer.selectedConclusion}" cssClass="selectedConclusion form-control" />
			<form:hidden path="question.id" id="questionId" name="questionId" value="${question.id}" cssClass="questionId form-control" />
			
			
		<div class="row">
		  <div class="col-xs-12 col-sm-6 col-md-10 text-center" style="margin-top:100px">
		  	You have attempted ${currentQuestionNumber} of ${totalQuestions} questions  <br/> when you are ready to attempt the next question click 'Continue' button bellow
		  	<br/>
		  	Thank you.
		  	<div class="form-group">
		  		<input type="submit" class="btn btn-default" value="Continue" />	
		  	</div>
		  </div>
		  <div class="col-md-4" style="background-color:gray-light" >

		  	

		  	<br/><br/><br/>

		  </div>
		</div>
		
		
		
		<br /> <br />
		
		<div class="modal-footer">
					
		</div>

</form:form>

