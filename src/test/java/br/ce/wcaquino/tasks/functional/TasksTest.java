package br.ce.wcaquino.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {

	private WebDriver acessarDriver() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8081/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() {
		WebDriver driver = acessarDriver();

		try {

			//add todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever descrição
			driver.findElement(By.id("task")).sendKeys("Teste Via Selenium");
			
			//escrever data 
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Sucess!", mensagem);
		}finally {			
			//fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		WebDriver driver = acessarDriver();

		try {

			//add todo
			driver.findElement(By.id("addTodo")).click();

			//escrever data 
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", mensagem);
		}finally {			
			//fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() {
		WebDriver driver = acessarDriver();

		try {
			//add todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever descrição
			driver.findElement(By.id("task")).sendKeys("Teste Via Selenium");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", mensagem);
		}finally {			
			//fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		WebDriver driver = acessarDriver();

		try {

			//add todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever descrição
			driver.findElement(By.id("task")).sendKeys("Teste Via Selenium");
			
			//escrever data 
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", mensagem);
		}finally {			
			//fechar o browser
			driver.quit();
		}
	}
	
}
