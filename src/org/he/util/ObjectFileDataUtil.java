package org.he.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.he.dto.DonwloadInfor;
import org.he.dto.DownLoadInforData;

/**
 * @author BenSon He
 * @email qing878@gmail.com ,qq 277803242
 * @since 19/11/2012
 */
public class ObjectFileDataUtil {
	private File file = null;

	public ObjectFileDataUtil(String fileName) throws FileNotFoundException {
		if (fileName == null)
			// avoid to create a file what name is null
			throw new FileNotFoundException("you have to input a file name");
		DLog.log(this.getClass(), "the temp file is " + fileName);
		file = new File(fileName);
	}

	public void writeObjectToFile(Object obj) {
		ObjectOutputStream objWtFile = null;
		try {
			objWtFile = new ObjectOutputStream(new FileOutputStream(file));
			objWtFile.writeObject(obj);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			DLog.log(DLog.ERROR, e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			DLog.log(DLog.ERROR, e);
		} finally {
			try {
				objWtFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				DLog.log(DLog.ERROR, e);
			}

		}
	}

	public Object readObjectFromFile() {
		ObjectInputStream objInFile = null;
		Object object = null;
		try {
			objInFile = new ObjectInputStream(new FileInputStream(file));
			System.out.println(objInFile);
			object = objInFile.readObject();
			System.out.println("object="+object);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			DLog.log(DLog.ERROR, e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			DLog.log(DLog.ERROR, e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			DLog.log(DLog.ERROR, e);
		} finally {
			try {
				objInFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				DLog.log(DLog.ERROR, e);
			}
		}
		return object;
	}

	public void removeDataFile() {
		file.delete();
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ObjectInputStream obj=new ObjectInputStream(new FileInputStream("D:\\Download4Http\\hbwyj3D.mkv.temp"));
		obj.readObject();
	}
}
