<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<script type="text/javascript">



	function finishOption4(){
		 var elapsedTime = $('.timer').text();
		 $('#elapsedTime').val(elapsedTime);
		 $('.timer').text('stop');
	}
	
	function selectOption(args){
		 $('#selectedOptionValue').val(args);
		 var elapsedTime = $('.timer').text();
		 $('#elapsedTime').val(elapsedTime);
		 $('.timer').text('stop');
		 document.getElementById('submitId').click();
	}
	
	function selectOption2(args){
		 $('#selectedOptionValue').val(args);	
		 var elapsedTime = $('.timer').text();
		 $('#elapsedTime').val(elapsedTime);
		 $('.timer').text('stop');
	}
	
	function selectOption4(args){
		 $('#selectedOptionValue').val(args);		
	}
	
	function selectPremise(args){
		 $('#selectedPremise').val(args);
	}

	
	function selectConclusion(args){
		 $('#selectedConclusion').val(args);
	}


	$(document)
	.ready(
			
			function() {
				
				$('.Submit').hide(); 
				
				if (window.history && window.history.pushState) {

				    $(window).on('popstate', function() {
				      var hashLocation = location.hash;
				      var hashSplit = hashLocation.split("#!/");
				      var hashName = hashSplit[1];

				      if (hashName !== '') {
				        var hash = window.location.hash;
				        if (hash === '') {
				          alert('Back button was pressed.');
				        }
				      }
				    });

				    window.history.pushState('forward', null, './#forward');
				  }
				
					
				setInterval(function() {

					
					var start = $('.timer').text();
					if(start != 'stop'){
						if(start <= 0){
							 document.getElementById('submitId').click();
							
							}
						start = start-1
					    $('.timer').text(start);
					}	
				}, 1000);
				
				var wrapper = $(".wrapper"); 
				
				$('.createfieldbutton').on('click', function() {
					$(wrapper).empty();
					$('.createfieldbutton').removeClass('active');
					$(this).addClass('active');
					
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
					$('.wrappertype1').empty();
					$('.createfieldbuttonType1').removeClass('active');
					$(this).addClass('active');
					var continueButton = '<input type="submit" class="btn btn-default" value="Continue" />'
					
						$('.wrappertype1')
							.append(continueButton);
				});
				
				$('.createfieldbuttonType2').on('click', function() {
					$(wrapper).empty();
					$('.createfieldbuttonType2').removeClass('active');
					$(this).addClass('active');
					
					var fieldCount = $(this).attr('data-id');
					for (var i = 0; i < fieldCount; i++) {
						var inputField = '<form:input path="answer.answerOptions[0].name"  cssClass="anwser-fields btn-block form-control"  name="mytext[]"/><br/>'
							$(wrapper) 
									.append(inputField.replace("answerOptions[0]","answerOptions["+i+"]"));
					}
					var continueButton = '<input type="submit" class="btn btn-default" value="Continue" />'
				
						$(wrapper)
							.append(continueButton);
					
						$('.createfieldbuttonType2').each(function(){
							$(this).prop('onclick',null).off('click');	
						});
					
					$(".anwser-fields").val("");
	
				});
				
				
				$('.triggerOption1').click(function(e){
					$('.selectedOptionValue').val(this.value)
				});
				
				 $("#output").width(640); 
				 $("#output").height(480);
				    
				    
			});
								 	
</script>

<form:form commandName="answer" modelAttribute="answer" action="summary" method="POST" enctype="multipart/form-data" cssClass="form-horizontal">
	
		<div class="row">
		  <div class="col-xs-12 col-sm-6 col-md-8">
		  <img id="output" src="data:image/png;base64,${question.base64Image}" alt="" class="img-rounded">
		  
		  </div>
		  <div class="col-md-4" style="background-color:gray-light" >
		  	${question.title} <br/>
		  	
		   
		   <label class= "timer" hidden="true" >${question.study.timeout}</label>

		  	<br/><br/><br/>
		  	<form:hidden path="elapsedTime" id="elapsedTime" name="elapsedTime" value="" cssClass="elapsedTime form-control" />
			<form:hidden path="selectedOptionValue" id="selectedOptionValue" name="optionValue" value="" cssClass="selectedOptionValue form-control" />
			<form:hidden path="selectedConclusion" id="selectedConclusion" name="selectedConclusion" value="" cssClass="selectedConclusion form-control" />
			<form:hidden path="question.id" id="questionId" name="questionId" value="${question.id}" cssClass="questionId form-control" />
			
		  	
		  	<c:if test="${question.type eq '3'}" >
		  		<br/>
		  		
			  	<c:forEach items="${question.questionOptions}" var="option">

					<div class="radio">
						<label> <form:radiobutton class="optionsRadios" onclick="selectOption('${option.name}')" 
						path="selectedRule"  name="optionsRadios" id="optionsRadios1" value="${option.id}" /> ${option.name}
						</label>
					</div>
				</c:forEach>	
						
		  	</c:if>
		  	
		  	
		  					<c:forEach var="i" begin="1" end="${question.numberOfPremises}">
				   <label> <form:checkbox path="selectedCheckBoxes"  value="${i}"/>  ${i}
				   </label><br/> 
				</c:forEach>
				
				
		  	
		  	<c:if test="${question.type eq '4'}" >
		  		<br/>
		  		<c:set var="count" value="0" scope="page" />
			  	<c:forEach items="${question.questionOptions}" var="option">

					<div class="radio">
						<label> <form:checkbox  class="optionsRadios" onclick="selectOption4('${option.name}')" 
						path="selectedCheckBoxes"  name="optionsRadios" id="optionsRadios1" value="${count}" /> ${option.name}
						</label>
					</div>
					<c:set var="count" value="${count + 1}" scope="page"/>
				</c:forEach>	
				<input type="submit" class="btn btn-default" value="Submit task" onclick="finishOption4()"/>
						
		  	</c:if>
		  	
			<c:if test="${question.type eq '1'}" >
			  	<c:forEach items="${question.questionOptions}" var="option">
						<a data-id="${option.name}" onclick="selectOption('${option.name}')"  class="btn btn-default text-left btn-block">
							<span class="pull-left">&nbsp;&nbsp;&nbsp;${option.name}</span>&nbsp;</a>			
				</c:forEach>
				<br/>
				<br/>
				<div class="wrappertype1"></div>
				
			</c:if>
			
			<c:if test="${question.type eq '2'}" >
			  	<c:forEach items="${question.questionOptions}" var="option">
						<a data-id="${option.name}" name="buttonType2" onclick="selectOption2('${option.name}')" class="triggerOption2 createfieldbuttonType2 btn btn-default text-left btn-block">
							<span class="pull-left">&nbsp;&nbsp;&nbsp;${option.name}</span>&nbsp;</a>			
				</c:forEach>
				<br/>
				<div class="wrapper"></div>
			</c:if>
		  </div>
		</div>
		
		<br /> <br />
	   <input type="submit" hidden="true" id="submitId" class="Submit btn btn-default" value="Continue" />
		


</form:form>

