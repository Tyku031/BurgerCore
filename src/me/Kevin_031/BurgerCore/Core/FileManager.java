package me.Kevin_031.BurgerCore.Core;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;


public class FileManager {
	// Root directory for all data managed by a FileManager.
	public String RootDirectory = "plugins/BurgerCore/Data/";
	// File is the root directory file.
	public File File;
	
	/** Manager for getting and setting external files. 
	 * Will create root directory if it doesn't exist. 
	 * All data will be placed in the plugins/Burgercore/Data folder. */
	public FileManager(String rootDirectory) {
		RootDirectory += rootDirectory.trim();
		File = new File(RootDirectory);
		if (!File.exists())
			File.mkdirs();
	}
	
	/** Get a file from the root directory, if file is not found return null. */
	public File GetFileFromDir(String fileName) {
		File FileInDir = new File(RootDirectory + fileName);
		if (FileInDir.exists())
			return FileInDir;
		else return null;
	}
	
	/** Get all files in a directory. */
	public File[] GetAllFilesFromDir() {
		File[] FilesInDir = File.listFiles();
		return FilesInDir;
	}
	
	/** Get all filtered files in a directory. */
	public File[] GetAllFilesFromDir(FileFilter filter) {
		File[] FilesInDir = File.listFiles(filter);
		return FilesInDir;
	}
	
	/** Get all filtered files in a directory. */
	public File[] GetAllFilesFromDir(FilenameFilter filter) {
		File[] FilesInDir = File.listFiles(filter);
		return FilesInDir;
	}
}