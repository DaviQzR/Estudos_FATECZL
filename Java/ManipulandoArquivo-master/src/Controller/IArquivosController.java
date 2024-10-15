package Controller;

import java.io.IOException;

public interface IArquivosController {
	public void readDir(String path) throws IOException;
	public void createFile(String path, String nome)throws IOException;
	public void readFile(String path, String nome) throws IOException;
	public void openFile(String path, String nome) throws IOException;
	
	
}
