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
        	System.out.println("El usuario se encuentra en el menu: "+currentMenu);
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId().toString()); //Define a quien le vamos a enviar el mensaje
            String texto=update.getMessage().getText();
           mandarMensaje(texto, update, message);
        }
    }
    @Override
    public String getBotUsername() {
        return "ISUMA_BOT";
    }
    public void mandarMensaje(String texto, Update update, SendMessage message)
    {
 
    	if(currentMenu==0)
    	{
    		message.setText("Bienvenido al Bot Calculadora."
    				+ "\nSeleccione una de las siguientes opciones:"
    				+ "\n1. Sumar dos números."
    				+ "\n2. Calcular la serie fibonacci.");
    		currentMenu++;
    		nums.clear();
    	}
    	if(currentMenu==1) 
    	{
    		if(validarOpcion(texto)==1)
    		{
    			message.setText("Ingrese el primer número: ");
    			 currentMenu++;
    		}
    		else if(validarOpcion(texto)==2)
    		{
    			message.setText("Funcionalidad no implementada, intente otro día.");
    			currentMenu=0;
    		}
    		else
    		{
    			nums.clear();
    			message.setText("Bienvenido al Bot Calculadora."
        				+ "\nSeleccione una de las siguientes opciones:"
        				+ "\n1. Sumar dos números."
        				+ "\n2. Calcular la serie fibonacci.");
    			currentMenu=1;
    		}
    	}
    	else if(currentMenu==2)
    	{
    		if(validarNumeros(texto))
    		{
    			message.setText("Ingrese el segundo número: ");
    			currentMenu++;
    		}
    		else
    		{
    			nums.clear();
    			message.setText("Bienvenido al Bot Calculadora."
        				+ "\nSeleccione una de las siguientes opciones:"
        				+ "\n1. Sumar dos números."
        				+ "\n2. Calcular la serie fibonacci.");
    			currentMenu=1;
    		}
    	}
    	else if(currentMenu==3) 
    	{
    		if(validarNumeros(texto))
    		{
    			message.setText("El resultado es: "+(nums.get(0)+nums.get(1)));
    			currentMenu=0;
    		}
    		else
    		{
    			nums.clear();
    			message.setText("Bienvenido al Bot Calculadora."
        				+ "\nSeleccione una de las siguientes opciones:"
        				+ "\n1. Sumar dos números."
        				+ "\n2. Calcular la serie fibonacci.");
    			currentMenu=1;
    		}
    	}
    	try 
    	{
    		execute(message); // Envia el mensaje
    	}catch (TelegramApiException e) 
    		{
    	    	e.printStackTrace();
    	    }
    	
    }

    public boolean validarNumeros(String texto)
    {
    	int n;
    	try
    	{
    		n=Integer.parseInt(texto);
    		
    	}catch(Exception e)
    	{
    		return false;
    	}
    	nums.add(n);
    	return true;
    }
    public int validarOpcion(String texto) 
    {
    	int n;
    	try
    	{
    		n=Integer.parseInt(texto);
    	}catch(Exception e)
    	{
    		return 0;
    	}
    	return n;
    }
}
