package com.vmgtool.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.vmgtool.entity.AnswerOptions;
import com.vmgtool.entity.Answer;
import com.vmgtool.entity.Exam;
import com.vmgtool.entity.ExamAnswer;
import com.vmgtool.entity.ExamAnswerOptions;
import com.vmgtool.entity.ExamUser;
import com.vmgtool.entity.Question;
import com.vmgtool.entity.Studies;
import com.vmgtool.services.ExamService;
import com.vmgtool.services.QuestionService;
import com.vmgtool.services.StudiesService;



@Controller
@RequestMapping
@Scope("session")
public class ExamController {

	@Autowired
	private StudiesService studiesService;

	@Autowired
	private ExamService examService;

	@Autowired
	private QuestionService questionService;
	
	private List<Question> questionsResponded = new ArrayList<Question>();
	
	@ModelAttribute("examuser")
	public ExamUser constructUser() {
		
		return new ExamUser();
	}

	@RequestMapping(value = "/studies/{id}/exam")
	public String login() {
		return "exam";
	}
	
	//show the test
	@RequestMapping(value = "/studies/{id}/exam/{examid}/")
	public String showExam(Model model,@ModelAttribute("examuser") ExamUser examUser, @PathVariable(value = "id") int id,@PathVariable(value = "examid") int examId){
		Studies study = studiesService.findOneWithQuestions(id);
		
		if (study.getQuestion() != null && !study.getQuestion().isEmpty()) {
			Question firstQuestion = study.getQuestion().iterator().next();
			model.addAttribute("question", firstQuestion);
			model.addAttribute("answer", new Answer());
		}
		
		return "exam-start";
	}

	//start a new test.
	@RequestMapping(value = "/studies/{id}/start")
	public String startExam(Model model,@ModelAttribute("examuser") ExamUser examUser, @PathVariable(value = "id") int id) {
			questionsResponded = new ArrayList<Question>();
			Studies study = studiesService.findOneWithQuestions(id);
			Exam exam = new Exam();
			exam.setStudy(study);
			exam.setExamUser(examUser);
			exam.setCreateDate(new Date());
			examService.save(exam);
		
		return "redirect:/pages/studies/"+id+"/exam/"+exam.getId()+"/";

	}

	//code to display the next question
	@RequestMapping(value = "/studies/{id}/exam/{examid}/next")
	public String nextQuestion(Model model, @ModelAttribute("answer") Answer answer,
			@PathVariable(value = "id") int id, @PathVariable(value = "examid") int examid) {
		Studies study = studiesService.findOneWithQuestions(id);
		if (study.getQuestion() != null && !study.getQuestion().isEmpty()) {

			boolean hasMoreQuestions = false;
			if(study.getRandom() == true){
				List<Question> qs = study.getQuestion();
				qs.removeAll(questionsResponded);
				if(!qs.isEmpty()){
					Question randomQuestion = qs.get(new Random().nextInt(qs.size()));
					hasMoreQuestions = true;
					model.addAttribute("question", randomQuestion);
					model.addAttribute("answer", new Answer());
				}else{
					hasMoreQuestions = false;
				}
			}else {
				for (Question question : study.getQuestion()) {
					if (question.getId().equals(answer.getQuestion().getId())
							|| (question.getId() < answer.getQuestion().getId())) {
						continue;
					} else {
						hasMoreQuestions = true;
						model.addAttribute("question", question);
						model.addAttribute("answer", new Answer());
						break;
					}
				}
			}

			if (!hasMoreQuestions) {
				Exam exam = examService.findOne(examid);
				model.addAttribute("exam", exam);
				return "exam-end";
			}
		}
		return "exam-start";
	}
	
	
	@RequestMapping(value = "/studies/{id}/exam/{examid}/summary")
	public String nextSummary(Model model, @ModelAttribute("answer") Answer answer,
			@PathVariable(value = "id") int id, @PathVariable(value = "examid") int examId) {
		Exam exam = examService.findOne(examId);
		ExamAnswer examAnswer = new ExamAnswer();
		
		Studies study = studiesService.findOneWithQuestions(id);
		Integer totalQuestions = study.getQuestion().size();
		
		Integer correctAnswers = exam.getCorrectAnswers();
		if(correctAnswers == null){
			correctAnswers = 0;
		}
		
	
		Question questionDB = questionService.findOne(answer.getQuestion().getId());
		
		examAnswer.setSelectedOptionValue(answer.getSelectedOptionValue());
		examAnswer.setSelectedConclusion(answer.getSelectedConclusion());
		examAnswer.setSelectedPremise(answer.getSelectedPremise());
		examAnswer.setSelectedRule(answer.getSelectedRule());
		examAnswer.setElapsedTime(questionDB.getStudy().getTimeout()-answer.getElapsedTime());
		examAnswer.setCreatedDate(new Date());
		examAnswer.setExam(exam);
		examAnswer.setQuestion(questionDB);
		
		if(answer.getAnswerOptions() != null){
			List<ExamAnswerOptions>  aOptions = new ArrayList<ExamAnswerOptions>();

			for(AnswerOptions opt : answer.getAnswerOptions() ){
				ExamAnswerOptions option = new ExamAnswerOptions();
				option.setName(opt.getName());
				option.setExamAnswer(examAnswer);
				aOptions.add(option);
			}
			examAnswer.setExamAnswerOptions(aOptions);

		}
		
		List<ExamAnswer>  exmAnswers = exam.getExamAnswers();
		if(exmAnswers == null)
			exmAnswers = new ArrayList<ExamAnswer>();
		
		exmAnswers.add(examAnswer);
		exam.getExamAnswers().addAll(exmAnswers);
		examAnswer.setCorrect("N");
		if(questionDB.getType().equals(new Integer("1")) ||
				questionDB.getType().equals(new Integer("2")) ||
				questionDB.getType().equals(new Integer("3"))){
			if(questionDB.getAnswer().getSelectedOptionValue().equals(answer.getSelectedOptionValue())){
				examAnswer.setSelectedOptionValue(answer.getSelectedOptionValue());
				correctAnswers++;
				examAnswer.setCorrect("Y");
			}else{
				//??
			}
		}
	
			if(questionDB.getType().equals(new Integer("4"))){
				if(questionDB.getAnswer().getSelectedOptionNumber().equals(answer.getSelectedCheckBoxes())){
					examAnswer.setSelectedOptionValue(answer.getSelectedCheckBoxes());
						examAnswer.setCorrect("Y");
						correctAnswers++;
				}else{
				
				}
			}

		
		exam.setCorrectAnswers(correctAnswers);
		examService.save(exam);
		questionsResponded.add(questionDB);
		
		model.addAttribute("totalQuestions", totalQuestions.toString());
		model.addAttribute("currentQuestionNumber", (questionsResponded.size()));
		model.addAttribute("answer", answer);
		return "exam-summary";
	}
	
	@RequestMapping(value = "/studies/{id}/showexams")
	public String showExams(Model model,
			@PathVariable(value = "id") int id) {
		Studies study = studiesService.findOne(id);		

		List<Exam> exams= examService.findByStudy(study);
		
		
		model.addAttribute("exams", exams);
		model.addAttribute("exam", new Exam());
		
		return "listexams";
	}
	
	@RequestMapping(value = "/studies/{id}/exam/{examId}/exportrow")
	public void exportRow(Model model,@PathVariable(value = "examId") int examid,HttpServletResponse response){
		
		response.setContentType("text/plain");
        response.setHeader("Content-disposition", "attachment; filename=\"" + "result" + ".txt\"");
     
		try {
		
			response.getWriter().write("Exp. Ref | "+"Participant Ref. | "+"Uni. | "+"Gender | "+"Age | "+"Sigh | "+"QuestionID | "+"Correct Answer | "+"Participants Answer | "+"Correct(y/n) | "+"Elapsed time | "+"Date TimeStamp\n");

		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
		Exam exam = examService.findOne(examid);
		Integer examId= exam.getId();
		String reference = exam.getExamUser().getParticipantReference();
		String university = exam.getExamUser().getUniversity();
		String gender = exam.getExamUser().getGender()	;
		Integer age = exam.getExamUser().getAge();
		String sigh = exam.getExamUser().getSighBasedDisability();
		for(ExamAnswer e : exam.getExamAnswers()){
			e.getQuestion().getId();
			e.getQuestion().getAnswer().getSelectedOptionValue();
			e.getSelectedOptionValue();
			e.getCorrect();
			e.getElapsedTime();
			exam.getStudy().getCreatedDate();
			
			try {
				response.getWriter().write(examId+" | "+reference+" |  "+university+" |  "+gender+" |  "+age+" |  "+sigh+" |  "+e.getQuestion().getId()+" |  "+e.getQuestion().getAnswer().getSelectedOptionValue()
						+" |  "+e.getSelectedOptionValue()+" |  "+e.getCorrect()+" |  "+e.getElapsedTime()+" |  "+e.getCreatedDate()+"\n");
			} catch (IOException e1) {
			
				e1.printStackTrace();
			}			
		}
		
		try {
			response.flushBuffer();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		return ;
	}
	
	@RequestMapping(value = "/studies/{id}/exam/exportall")
	public void exportAll(Model model,@PathVariable(value = "id") int studyId,HttpServletResponse response){
		
		response.setContentType("text/plain");
        response.setHeader("Content-disposition", "attachment; filename=\"" + "result" + ".txt\"");
     
		try {
		
			response.getWriter().write("Exp. Ref | "+"Participant Ref. | "+"Uni. | "+"Gender | "+"Age | "+"Sigh | "+"QuestionID | "+"Correct Answer | "+"Participants Answer | "+"Correct(y/n) | "+"Elapsed time | "+"Date TimeStamp\n");

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Studies study = studiesService.findOne(studyId);		
		List<Exam> exams = examService.findByStudy(study);
		for(Exam exam : exams){
			String examId= exam.getExamUser().getExperimentalReference();
			String reference = exam.getExamUser().getParticipantReference();
			String university = exam.getExamUser().getUniversity();
			String gender = exam.getExamUser().getGender()	;
			Integer age = exam.getExamUser().getAge();
			String sigh = exam.getExamUser().getSighBasedDisability();
			for(ExamAnswer e : exam.getExamAnswers()){
				e.getQuestion().getId();
				//missing
				e.getQuestion().getAnswer().getSelectedOptionValue();
				e.getSelectedOptionValue();
				e.getCorrect();
				e.getElapsedTime();
				exam.getStudy().getCreatedDate();
				
				try {
					response.getWriter().write(examId+" | "+reference+" |  "+university+" |  "+gender+" |  "+age+" |  "+sigh+" |  "+e.getQuestion().getId()+" |  "+e.getQuestion().getAnswer().getSelectedOptionValue()
							+" |  "+e.getSelectedOptionValue()+" |  "+e.getCorrect()+" |  "+e.getElapsedTime()+" |  "+exam.getCreateDate()+"\n");
				} catch (IOException e1) {
			
					e1.printStackTrace();
				}			
			}
		}
		try {
			response.flushBuffer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ;
	}
	
	@RequestMapping(value = "/studies/{id}/exam/{examid}/detail")
	public String detailExam(Model model,
			@PathVariable(value = "examid") int examid) {
		Exam exam = examService.findOne(examid);
		Set<ExamAnswer> result = new HashSet<ExamAnswer>();
		result.addAll(exam.getExamAnswers());
		for(ExamAnswer ea : result){
			ea.getQuestion().getBase64Image();
		}
		model.addAttribute("answers",result);
		
		return "exam-detail";
	}
}
