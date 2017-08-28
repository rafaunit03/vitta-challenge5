projeto = {
	limpar : function(){
		$("#nome").val('');	
		$("#x_inicial").val('');
		$("#x_final").val('');
		$("#y_inicial").val('');
		$("#y_final").val('');
	},

	save : function (){
	  var nome = $("#nome").val();	
	  var x_inicial = $("#x_inicial").val();
	  var x_final = $("#x_final").val();
	  var y_inicial = $("#y_inicial").val();
	  var y_final = $("#y_final").val();
	  var erro = 0;
	  var msg = "";
	  if(nome==""){
		  msg += "Informe o campo nome\n";
		  erro++;
	  }
	  if(isNaN(x_inicial) || x_inicial==""){
		  msg += "Informe o campo x_inicial\n";
		  erro++;
	  }
	  if(isNaN(x_final) || x_final==""){
		  msg += "Informe o campo x_final\n";
		  erro++;
	  }
	  if(isNaN(y_inicial) || y_inicial==""){
		  msg += "Informe o campo y_inicial\n";
		  erro++;
	  }
	  if(isNaN(y_final) || y_final==""){
		  msg += "Informe o campo y_final\n";
		  erro++;
	  }
	  if(erro > 0){
		  alert(msg);
		  return false;
	  }
	  
	  var objJson = { 'name' : nome,
			  		  'start' : {"x":x_inicial,"y":y_inicial},
			  		  'end' : {"x":x_final,"y":y_final}
	  };			  	
						
	  var dataFields = encodeURIComponent( JSON.stringify( objJson ) );		  
	  var url = 'sistema/save';
	  $.ajax(
		  {
			  type 	   : 'POST',
			  url  	   : url,
			  data 	   : 'strPost='+dataFields,
			  dataType   : 'JSON',
			  beforeSend : function(){
			  },
			  success : function( response ){
				var json = eval(response);
				if(json.error=="false"){		
					alert('Salvo com sucesso!');
					projeto.listAll();
					projeto.limpar();
				}else{
					alert(json.msg_error);
				}
			  },
			  error : function( error ){
				 
			  }
		  }
	  );
	},

	listAll :function (){	
	  var objJson = { };						
		
	  var dataFields = encodeURIComponent( JSON.stringify( objJson ) );		  
	  var url = 'sistema/listAll';
	  $.ajax(
		  {
			  type 	   : 'POST',
			  url  	   : url,
			  data 	   : 'strPost='+dataFields,
			  dataType   : 'JSON',
			  beforeSend : function(){
			  },
			  success : function( response ){
				  var json = eval(response);
				  if(json.count==0){
					  alert("Nenhum Território foi encontrado!");
					  $("#impressao").html('<canvas id="canvas" width="1000" height="800"></canvas>');
				  }else{
					  var vetData = eval(json.data);
					  $("#impressao").html('<canvas id="canvas" width="1000" height="800"></canvas>');
					  var canvas = document.getElementById('canvas');	
					  $.each(vetData, function(id, campo) {
						  var name = campo.name;
						  var id = campo.id;
						  var x_inicio = campo.start.x;
						  var y_inicio = campo.start.y;
						  var x_fim = campo.end.x;
						  var y_fim = campo.end.y;
						  var painted_area = campo.painted_area;
						  var painted_squares = campo.painted_squares;
						  projeto.draw(name,id,x_inicio,y_inicio,x_fim,y_fim,painted_area,canvas,painted_squares);						  
					  });
				  }
			  },
			  error : function( error ){
				 
			  }
		  }
	  );
	},	
	
	draw : function (name,id,x_inicio,y_inicio,x_fim,y_fim,painted_area,canvas,painted_squares) {
      if (canvas.getContext) {
        var ctx = canvas.getContext('2d');		
        ctx.strokeRect(x_inicio,y_inicio,(x_fim-x_inicio),(y_fim-y_inicio));	
    	ctx.fillStyle = '#000000';
    	ctx.fillText(name+"("+id+")",x_inicio+2,y_inicio+10);
    	if(painted_area>0){
    		 var vetPainted = eval(painted_squares);
        	 $.each(vetPainted, function(id, campo) {
				  var x = campo.x;	
				  var y = campo.y;	
				  ctx.fillStyle = '#ffb6c1';
		          ctx.fillRect(x,y,1,1);
			 });    		
    	}
      }
	},
	
	remove :function (){
		 if(confirm("Deseja realmente apagar território ?")){
			  var id = $("#id").val();
			  if(isNaN(id) || id==""){
				 alert("Informe um ID válido para remover");
				 return false;
			  }
			  var objJson = {"id":id};						
			  var dataFields = encodeURIComponent( JSON.stringify( objJson ) );		  
			  var url = 'sistema/remove';
			  $.ajax(
				  {
					  type 	   : 'POST',
					  url  	   : url,
					  data 	   : 'strPost='+dataFields,
					  dataType   : 'JSON',
					  beforeSend : function(){
					  },
					  success : function( response ){		 
						  var json = eval(response);
							if(json.error=="false"){		
								alert('Apagado com sucesso!');
								projeto.listAll();
								projeto.limpar();
								$("#id").val('');
							}else{
								alert("Falha ao tentar remover");
							}
					  },
					  error : function( error ){
						 
					  }
				  }
			  );
		  }
	},
		
	find :function (){
	  var id = $("#id").val();
	  if(isNaN(id) || id==""){
		 alert("Informe um ID válido para remover");
		 return false;
	  }
	  var objJson = {"id":id};						
	  var dataFields = encodeURIComponent( JSON.stringify( objJson ) );		  
	  var url = 'sistema/find';
	  $.ajax(
		  {
			  type 	   : 'POST',
			  url  	   : url,
			  data 	   : 'strPost='+dataFields,
			  dataType   : 'JSON',
			  beforeSend : function(){
			  },
			  success : function( response ){		 
				  var json = eval(response);
				  if(json.error=="false"){
					  var id = json.data.id;
					  var name = json.data.name;
					  var x_inicial = json.data.start.x;
					  var y_inicial = json.data.start.y;
					  var x_final = json.data.end.x;
					  var y_final = json.data.end.y;
					  var area = json.data.area;
					  var painted_area = json.data.painted_area;
					  alert("id:"+id+"\n"+
							"name:"+name+"\n"+
							"Pos X:"+x_inicial+" a "+x_final+"\n"+
							"Pos Y:"+y_inicial+" a "+y_final+"\n"+
							"Paintado ?:"+painted_area+"\n"+
							"Área:"+area);
				  }else{
					  alert(json.msg_error);
				  }
			  },
			  error : function( error ){
				 
			  }
		  }
	  );
	},		
	
	paint :function (){
	  var x = $("#x_pintar").val();
	  var y = $("#y_pintar").val();
	  if(isNaN(x) || x=="" || isNaN(y) || y==""){
		 alert("Informe o x e y válido para pintar");
		 return false;
	  }
	  var objJson = {"x":x,"y":y};						
	  var dataFields = encodeURIComponent( JSON.stringify( objJson ) );		  
	  var url = 'sistema/paint';
	  $.ajax(
		  {
			  type 	   : 'POST',
			  url  	   : url,
			  data 	   : 'strPost='+dataFields,
			  dataType   : 'JSON',
			  beforeSend : function(){
			  },
			  success : function( response ){		 
				  var json = eval(response);
				  if(json.error=="false"){
					  alert('Pintado com sucesso!');
					  projeto.listAll();
				  }else{
					  alert(json.msg_error);
				  }
			  },
			  error : function( error ){
				 
			  }
		  }
	  );
	}
}