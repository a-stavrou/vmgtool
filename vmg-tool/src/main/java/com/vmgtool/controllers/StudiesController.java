package com.vmgtool.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vmgtool.entity.Answer;
import com.vmgtool.entity.AnswerOptions;
import com.vmgtool.entity.Question;
import com.vmgtool.entity.QuestionOptions;
import com.vmgtool.entity.Studies;
import com.vmgtool.services.AnswerService;
import com.vmgtool.services.QuestionService;
import com.vmgtool.services.StudiesService;
import com.vmgtool.services.UserService;

@Controller
@RequestMapping
public class StudiesController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private StudiesService studiesService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private AnswerService answerService;

	@ModelAttribute("study")
	public Studies constructStudy() {
		return new Studies();
	}
	@ModelAttribute("studyQuestion")
	public Question constructQuestion() {
		return new Question();
	}
	@RequestMapping("/studies")
	public String showStudies(Model model, Principal principal){
		String name = principal.getName();
		model.addAttribute("user", userService.findOneWithStudies(name));
		return "studies";
	}
	
	@RequestMapping(value="/studies", method=RequestMethod.POST)
	public String doAddStudy(Model model, @Valid @ModelAttribute("study") Studies study, BindingResult result, Principal principal){
		if(result.hasErrors()){
			return showStudies(model, principal);
		}
		String name = principal.getName();
		System.out.println(name);
		studiesService.save(study, name);
		return "redirect:/pages/studies.html";
	}

	
	@RequestMapping("/studies/{id}/")
	public String currStudy (Model model,@PathVariable int id){
		List<Question> questions = studiesService.findOneWithQuestions(id).getQuestion();

		model.addAttribute("studyQuestions", questions);
		System.out.println(questions.size());
		Question question = new Question();
		List<QuestionOptions> questionOptions = new ArrayList<QuestionOptions>();
		questionOptions.add(new QuestionOptions());
		questionOptions.add(new QuestionOptions());


		question.setQuestionOptions(questionOptions);
		
		model.addAttribute("question",new Question());
		
		return "current-study";
	}
	
	@RequestMapping(value="/studies/{id}/questions", method=RequestMethod.POST)
	public String addQuestion(@PathVariable int id,Model model, @Valid @ModelAttribute("question") Question question, BindingResult result, Principal principal){
		
		try {
			question.setImage(question.getImageUpload().getBytes());
			question.getBase64Image();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Question dbQuestion = null;
		if(question.getId() != null){
			dbQuestion = questionService.findOne(question.getId());
			//remove all options because they will be inserted again
			dbQuestion.getQuestionOptions().clear();
			questionService.save(dbQuestion);

		}

		Studies study = studiesService.findOne(id);		
		List<QuestionOptions> options = question.getQuestionOptions();	
		List<QuestionOptions> optionsCopy = new ArrayList<QuestionOptions>();	
		
		Answer answer = new Answer();
		answer.setSelectedOptionNumber(question.getAnswer().getSelectedOptionNumber());
		Integer i = 0;
		String answerDescription = "";
		for(QuestionOptions option : options){
			
			QuestionOptions questionOption = new QuestionOptions();
			questionOption.setName(option.getName());
			optionsCopy.add(questionOption);
			if(question.getType().equals(new Integer(4))){
				if(question.getAnswer().getSelectedOptionNumber().contains(i.toString())){
					answer.setSelectedOptionValue(option.getName());
					if(!answerDescription.equals(""))
						answerDescription+=",";
					answerDescription+=option.getName();
				}
				i++;
			}else{
				if(question.getAnswer().getSelectedOptionNumber().equals(i.toString())){
					answer.setSelectedOptionValue(option.getName());
					if(answerDescription != null && !("".equals(answerDescription))){
						answerDescription+=",";

					}
					answerDescription+=option.getName();
				}
				i++;
			}
			
		}
		answer.setSelectedOptionValue(answerDescription);
		answer.setOptionsDescriptions(answerDescription);
		answer.setSelectedPremise(question.getAnswer().getSelectedPremise());
		
		
		question.setStudy(study);
		question.setAnswer(null);

		
		question.getQuestionOptions().clear();
		question = questionService.save(question);
		
		
		for(QuestionOptions option : optionsCopy){
			option.setQuestion(question);
		}
		answer.setQuestion(question);
		question.setAnswer(answer);

		question.getQuestionOptions().clear();
		question.getQuestionOptions().addAll(optionsCopy);
		//question.setExamAnswers(dbQuestion.getExamAnswers());
		questionService.save(question);
		study = studiesService.findOneWithQuestions(id);
		
		model.addAttribute("studyQuestions",study.getQuestion());
		model.addAttribute("studyQuestion",new Question());

		return "redirect:/pages/studies/"+id+"/";

		
	}
	
	@RequestMapping(value="/studies/{id}/editquestions", method=RequestMethod.POST)
	public String editQuestion(@PathVariable int id,Model model, @Valid @ModelAttribute("question") Question question, BindingResult result, Principal principal){
		
		if(question.getId() != null){
			Question dbQuestion = questionService.findOne(question.getId());
			
			dbQuestion.getQuestionOptions().clear();
			questionService.save(dbQuestion);
			
			answerService.delete(dbQuestion.getAnswer());

			
			if(question.getImageUpload().getSize() > 0 ){
				try {
					question.setImage(question.getImageUpload().getBytes());
					question.getBase64Image();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				question.setImageUpload(dbQuestion.getImageUpload());
				question.setImage(dbQuestion.getImage());
				question.getBase64Image();

			}

		}
		
		
		

		Studies study = studiesService.findOne(id);		
		List<QuestionOptions> options = question.getQuestionOptions();	
		List<QuestionOptions> optionsCopy = new ArrayList<QuestionOptions>();	

		Answer answer = new Answer();
		answer.setSelectedOptionNumber(question.getAnswer().getSelectedOptionNumber());
		answer.setQuestion(question);
		
		
		Integer i = 0;
		for(QuestionOptions option : options){
			QuestionOptions questionOption = new QuestionOptions();
			questionOption.setName(option.getName());
			optionsCopy.add(questionOption);
			
			if(question.getAnswer().getSelectedOptionNumber().equals(i.toString())){
				answer.setSelectedOptionValue(option.getName());
			}
			i++;
			
		}
		question.setAnswer(null);
		
		
		question.setStudy(study);
	
		question.getQuestionOptions().clear();
		question = questionService.save(question);
		
		for(QuestionOptions option : optionsCopy){
			option.setQuestion(question);
		}
		question.setAnswer(answer);
		
		question.getQuestionOptions().clear();
		question.getQuestionOptions().addAll(optionsCopy);
		questionService.save(question);
		study = studiesService.findOneWithQuestions(id);
		
		model.addAttribute("studyQuestions",study.getQuestion());

		return "redirect:/pages/studies/"+id+"/";
		
	}
	
	@RequestMapping("/studies/remove/{id}")
	public String removeStudy(@PathVariable int id){
		
		Studies study = studiesService.findOne(id);
		studiesService.delete(study);
		return "redirect:/pages/studies.html";
	}
	
	@RequestMapping("/studies/{studyid}/questions/remove/{questionid}")
	public String removeQuestion(Model model ,@PathVariable(value="studyid") int id, @PathVariable(value="questionid") int questionid){
		
		Question question = questionService.findOne(questionid);
		questionService.delete(question);
		return "redirect:/pages/studies/"+id+"/";
	}
	
	@RequestMapping("/studies/{studyid}/questions/{questionid}/preview")
	public String previewQuestion(Model model ,@PathVariable(value="studyid") int id, @PathVariable(value="questionid") int questionId){
		
		Question question = questionService.findOne(questionId);
		Answer answer = new Answer();
		List<AnswerOptions> answers = new ArrayList<AnswerOptions>();
		
		answers.add(new AnswerOptions());
		answers.add(new AnswerOptions());
		answers.add(new AnswerOptions());
		answers.add(new AnswerOptions());
		
		answer.setAnswerOptions(answers);

		model.addAttribute("question",question);
		model.addAttribute("answer", new Answer());
		
		return "questions-preview";
	}
	
	@RequestMapping(value="/studies/{studyid}/questions/{questionid}/preview/update", method=RequestMethod.POST)
	public String previewUpdateQuestion(Model model ,@ModelAttribute("answer") Answer answer,@PathVariable(value="studyid") int id, @PathVariable(value="questionid") int questionId,BindingResult result, Principal principa){
		Question q = questionService.findOne(questionId);
		
	
		if(q.getAnswer() == null ){
			if(answer.getAnswerOptions() != null){
				for(AnswerOptions option : answer.getAnswerOptions()){
					option.setAnswer(answer);
				}
			}
			
			
			q.setAnswer(answer);
			answer.setQuestion(q);
			answerService.save(answer);
	
			
		}else{
		
			Answer ansDB = answerService.findByQuestion(q);
			
			if(!q.getType().equals(new Integer("3"))){
				ansDB.getAnswerOptions().clear();
				
				ansDB = answerService.save(ansDB);
				
			
				if(answer.getAnswerOptions() != null){
					for(AnswerOptions option : answer.getAnswerOptions()){
						option.setAnswer(ansDB);
						option.setId(null);
					}
					ansDB.getAnswerOptions().addAll(answer.getAnswerOptions());
				}
			}
	
			
			
			ansDB.setSelectedConclusion(answer.getSelectedConclusion());
			ansDB.setSelectedOptionValue(answer.getSelectedOptionValue());
			ansDB.setSelectedPremise(answer.getSelectedPremise());
			ansDB.setSelectedRule(answer.getSelectedRule());
			
			if(q.getType().equals(new Integer("2"))){
				ansDB.setSelectedRule(answer.getAnswerOptions().size());
			}
			
			answerService.save(ansDB);
			
		}

		model.addAttribute("question",q);

		
		return "redirect:/pages/studies/"+id+"/";
	}

	@RequestMapping(value = "/studies/{studyid}/questions/{questionid}", headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<String> showDetail(@PathVariable(value="studyid") int id, @PathVariable(value="questionid") int questionId) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		
		Question question = questionService.findOne(questionId);
    	return new ResponseEntity<String>(question.toJson(), headers, HttpStatus.OK);
	}
}
