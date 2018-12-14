//FUNCOES GERAIS
function validaDados(form){
    
    var resultado = true;
    for (i=0;i<form.length;i++){     
        //NAO ACEITA VAZIO 
        //NAO ACEITA MENOR QUE 2 CARACTERES
        if(form[i].getAttribute("validar") == "1"){
            if ( (form[i].value == "") || (form[i].value.length < 2) ){
                mostraMsgValidacao(form[i]);             
                resultado = false;
                break;
            }
        }     
        //NAO ACEITA VAZIO 
        //NAO ACEITA 0
        if(form[i].getAttribute("validar") == "2"){
            if ( (form[i].value == "") || (form[i].value == "0") ){              
                mostraMsgValidacao(form[i]);
                resultado = false;
                break;
            }
        }               
        //NAO ACEITA VAZIO 
        //NAO ACEITA DATA INVALIDA
        if(form[i].getAttribute("validar") == "3"){
            if ( (form[i].value == "") || (dataInvalida(form[i].value)) ){               
                mostraMsgValidacao(form[i]);  
                resultado = false;
                break;
            }
        }
        //NAO ACEITA VAZIO 
        if(form[i].getAttribute("validar") == "4"){
            if ( form[i].value == "" ){
                mostraMsgValidacao(form[i]);
                resultado = false;
                break;
            }
        }      
        //SO ACEITA VALOR NUMERICO
        if(form[i].getAttribute("validar") == "5"){
            if ( form[i].value > 0 ){
                mostraMsgValidacao(form[i]);
                resultado = false;
                break;
            }
        }  
    }      
	
    return resultado;
}

function mostraMsgValidacao(formElem){
    var nomeCampo;
    if(formElem.id != ""){
        nomeCampo = formElem.id;
    }else if(formElem.name != ""){
        nomeCampo = formElem.name;
    }                           
    alert("O campo [" + nomeCampo + "] é obrigatorio e contém um valor inválido!");
    formElem.focus();
}

