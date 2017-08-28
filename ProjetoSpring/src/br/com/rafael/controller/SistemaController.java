package br.com.rafael.controller;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.rafael.model.Functions;
import br.com.rafael.model.QuadradoModel;
import br.com.rafael.model.Quadrado;
@Controller
@RequestMapping("/sistema")
public class SistemaController {
	  @RequestMapping(value="/save", method=RequestMethod.POST)
	  @ResponseBody
	  public String save(@RequestParam(value = "strPost") String strPost) {
		JSONObject obj = new JSONObject(strPost);
		String nome = obj.get("name").toString();	 
		JSONObject start = obj.getJSONObject("start");
		JSONObject end = obj.getJSONObject("end");		
		int x_inicial = Integer.parseInt(start.get("x").toString());
		int x_final = Integer.parseInt(end.get("x").toString());
		int y_inicial = Integer.parseInt(start.get("y").toString());
		int y_final = Integer.parseInt(end.get("y").toString());
		Quadrado quadrado = new Quadrado(nome, x_inicial, x_final, y_inicial, y_final);	 
		QuadradoModel m = new QuadradoModel();		
		if(m.addMap(quadrado)){	
			return m.getStrJson(quadrado);			
		}else{
			return "{\"Erro\":\"true\",\"msg_error\":\""+Functions.toUtf8(m.getMsg_erro())+"\"}";
		}
	 }		
 
	 @RequestMapping(value = "/listAll", method = RequestMethod.POST)
	 @ResponseBody
	 public String listAll(@RequestParam(value = "strPost") String strPost) {	 	  
		QuadradoModel m = new QuadradoModel();
		return m.getStrList();
	 }	 
	 
	 @RequestMapping(value = "/remove", method = RequestMethod.POST)
	 @ResponseBody
	 public String remove(@RequestParam(value = "strPost") String strPost) {
		JSONObject obj = new JSONObject(strPost);
		QuadradoModel m = new QuadradoModel();
		int id = Integer.parseInt(obj.get("id").toString());
		return m.removeSquare(id);	
	 }
	 
	 @RequestMapping(value = "/find", method = RequestMethod.POST)
	 @ResponseBody
	 public String find(@RequestParam(value = "strPost") String strPost) {
		JSONObject obj = new JSONObject(strPost);
		QuadradoModel m = new QuadradoModel();
		int id = Integer.parseInt(obj.get("id").toString());
		Quadrado q = m.find(id);
		if(q==null){
			return "{\"error\":\"true\",\"msg_error\":\""+Functions.toUtf8("'territórios / não encontrados ': este território não foi encontrado.")+"\"}";
		}else{
			return m.getStrJson(q);	
		}
	 }
	 
	 @RequestMapping(value = "/paint", method = RequestMethod.POST)
	 @ResponseBody
	 public String paint(@RequestParam(value = "strPost") String strPost) {
		JSONObject obj = new JSONObject(strPost);
		QuadradoModel m = new QuadradoModel();
		int x = Integer.parseInt(obj.get("x").toString());
		int y = Integer.parseInt(obj.get("y").toString());
		if(!m.paint(x,y)){
			return "{\"error\":\"true\",\"msg_error\":\""+Functions.toUtf8("'territórios / não encontrados ': este território não foi encontrado.")+"\"}";
		}else{
			
			return "{\"data\": {\"x\": "+x+","
							+ "\"y\": "+y+","
							+ "\"painted\": true},"
				+ "\"error\":\"false\"}";
		}
	 }
	 
	 
	 
}
