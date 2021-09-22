/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.est;

import java.util.ArrayList;
import java.util.List;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
/**
 *
 * @author ecampohermoso
 */
public class HelloWorldBot extends TelegramLongPollingBot {
	private int currentMenu=0;
	private ArrayList <Integer>nums;
	private String lastMSG="";
	private String[] mensajes= {"Bienvenido al Bot Calculadora."
			+ "\nSeleccione una de las siguientes opciones:"
			+ "\n1. Sumar dos números."
			+ "\n2. Calcular la serie fibonacci.",
			"Ingrese el primer número: ", 
			"Ingrese el segundo número: "};
	
	public HelloWorldBot() 
	{
		currentMenu=0;
		nums= new ArrayList<Integer>();
	}

    @Override
    public String getBotToken() {
        return "1970441371:AAGIEE7oMuWkKpjykhbhY_RobZs1SSIwFOI";
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("Llego mensaje: " + update.toString());
        if(update.hasMessage()) { // Verificamos que tenga mensaje
            // Creo el objeto para enviar un mensaje
            SendMessage message = new SendMessage();
            //Define a quien le vamos a enviar el mensaje
            message.setChatId(update.getMessage().getChatId().toString()); 
            // Obtiene el mensaje del usuario
            String texto=update.getMessage().getText();
          
            mandarMensaje(texto,update,message);
            
        }
    }
    @Override
    public String getBotUsername() {
        return "ISUMA_BOT";
    }
    public void mandarMensaje(String texto, Update update, SendMessage message)
    { 	
    	SendMessage message2 = new SendMessage();
    	message2.setChatId(update.getMessage().getChatId().toString());
    	message2.setText("");
    	System.out.println("lastMSG: "+lastMSG);
    	for(int i=0;i<mensajes.length;i++)
    	{
    		if(lastMSG.equals(""))
    		{
    		
    			message.setText(mensajes[0]);
    			break;
    		}
    		else if(lastMSG.equals(mensajes[i]))
    		{
    			if(lastMSG.equals(mensajes[0]) && validarEntrada(texto)==2)
    			{
    				message.setText("Funcionalidad no implementada, intente otro día.");
    				break;
    			}
    			else if(lastMSG.contains("segundo") && validarEntrada(texto)!=-1)
    			{
    				int res=nums.get(0)+nums.get(1);
    				message.setText("El resultado es: "+res);
    				break;
    			}
    			
    			else if(validarEntrada(texto)!=-1)
    			{
    				message.setText(mensajes[i+1]);
    				break;
    			}
    			else
    			{
    				message.setText(mensajes[0]);
    				nums.clear();
    				break;
    			}
    		} 
    	}
    	if(message.getText().contains("El resultado es: ") || message.getText().contains("Funcionalidad"))
    	{
			System.out.println("Mensaje final");
			message2.setText(mensajes[0]);
			nums.clear();
    	}
    	try
    	{
    		
    		execute(message);
    		lastMSG=message.getText();
    		if(!message2.getText().equals(""))
    		{
    			execute(message2);
    			lastMSG=message2.getText();
    		}
    	}
    	catch(TelegramApiException e)
    	{
    		e.printStackTrace();
    	}
    }
    public int validarEntrada(String entrada)
    {
    	int valor;
    	try
    	{
    		valor=Integer.parseInt(entrada);
    	}
    	catch(Exception e)
    	{
    		return -1;
    	}
    	
    	if(lastMSG.equals(mensajes[0]) && valor>0 && valor<3)
    	{
    		return valor;
    	}
    	else if(lastMSG.equals(mensajes[1]) || lastMSG.equals(mensajes[2]))
    	{
    		nums.add(valor);
    		return valor;
    	}
    	return -1;
    }
}
