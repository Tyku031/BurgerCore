package me.Kevin_031.BurgerCore.Core;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;


public class FileManager {
	// Root directory for all data managed by a FileManager.
	private String rootDirectory = "plugins/BurgerCore/";
	// File is the root directory file.
	private File file;
	
	/** Manager for getting and setting external files. 
	 * Will create root directory if it doesn't exist. 
	 * All data will be placed in the plugins/Burgercore/ + directory folder. */
	public FileManager(String directory) {
		rootDirectory += directory.trim();
		file = new File(rootDirectory);
		if (!file.exists())
			file.mkdirs();
	}
	
	/** Get a file from the root directory, if file is not found create a new file and return it. */
	public File getOrCreateFile(String fileName) {
		File f = new File(rootDirectory + fileName);
		if (!f.exists())
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return f;
	}
	
	/** Get a file from the root directory, if file is not found return null. */
	public File getFile(String fileName) {
		File f = new File(rootDirectory + fileName);
		if (f.exists())
			return f;
		else return null;
	}
	
	/** Get all files in a directory. */
	public File[] getAllFiles() {
		File[] files = file.listFiles();
		return files;
	}
	
	/** Get all filtered files in a directory. */
	public File[] getAllFiles(FileFilter filter) {
		File[] files = file.listFiles(filter);
		return files;
	}
	
	/** Get all filtered files in a directory. */
	public File[] getAllFiles(FilenameFilter filter) {
		File[] files = file.listFiles(filter);
		return files;
	}
}