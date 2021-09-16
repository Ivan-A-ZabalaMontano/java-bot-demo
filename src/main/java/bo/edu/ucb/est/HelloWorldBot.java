/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.est;

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

    @Override
    public String getBotToken() {
        return "";
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("Llego mensaje: " + update.toString());
        if(update.hasMessage()) { // Verificamos que tenga mensaje
            // Creo el objeto para enviar un mensaje
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId().toString()); //Define a quien le vamos a enviar el mensaje
            if(update.getMessage().getText().contains(" y "))
            {
            	System.out.println("Mando numeros");
            	System.out.println("El usuario Mando: "+update.getMessage().getText());
            	
            	message.setText(sumarNumeros(update.getMessage().getText()));
            	
            }
            else
            {
            	System.out.println("No mando numeros");
            	message.setText("Hola " + update.getMessage().getFrom().getFirstName()+", para realizar una suma ingresa dos numeros con el siguiente formato: 5 y 3");
            }
            try {
                execute(message); // Envia el mensaje
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public String getBotUsername() {
        return "ISUMA_BOT";
    }
    public String sumarNumeros(String numeros)
    {
    	int n1=0;
    	int n2=0;
    	try
    	{
    	String[] vec;
    	vec=numeros.split(" y ");
    	n1=Integer.parseInt(vec[0]);
    	n2=Integer.parseInt(vec[1]);
    	}catch(Exception e)
    	{
    		return "Los valores ingresados no son correctos. Vuelva a ingresar los valores con el formato correspondiente por favor";
    	}
    	return("La suma es: "+new Suma(n1,n2).realizarSuma());
    }
}
