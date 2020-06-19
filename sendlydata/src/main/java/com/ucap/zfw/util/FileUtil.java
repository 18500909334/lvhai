package com.ucap.zfw.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil {
	/**
     * 文件打压缩包
     * @param files
     * @param Name
     * @return
     * @throws Exception
     */
    public static String zipFile(List<File> files, String Name)
            throws Exception {
        ZipOutputStream zipOut=null;
        FileOutputStream fous=null;
        /**创建一个临时压缩文件,我们会把文件流全部注入到这个文件中, 这里的文件你可以自定义是.rar还是.zip*/
        String zipName = Name + System.currentTimeMillis();
        File temp = File.createTempFile(zipName, ".zip");
        try {
            //创建文件输出流
            fous = new FileOutputStream(temp);
            zipOut = new ZipOutputStream(fous);
            //压缩打包
            zipFileToOutputStream(files, zipOut);
            return temp.getPath();
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(zipOut!=null){
                zipOut.close();
            }
            if(fous !=null){
                fous.close();
            }
        }
        return null;
    }

    /**
     * 把接受的全部文件打成压缩包
     * @param files<File>;
     * @param outputStream
     */
    public static void zipFileToOutputStream(List<File> files, ZipOutputStream outputStream) {
        int size = files.size();
        for(int i = 0; i < size; i++) {
            File file = (File) files.get(i);
            inputFile(file, outputStream);
        }
    }


    /**
     * 根据输入的文件与输出流对文件进行打包
     * @param inputFile
     * @param ouputStream
     */
    public static void inputFile(File inputFile,ZipOutputStream ouputStream) {
        try {
            if(inputFile.exists()) {
                /**如果是目录的话这里是不采取操作的*/
                if (inputFile.isFile()) {
                    FileInputStream IN = new FileInputStream(inputFile);
                    BufferedInputStream bins = new BufferedInputStream(IN, 512);
                    //org.apache.tools.zip.ZipEntry
                    ZipEntry entry = new ZipEntry(inputFile.getName());
                    ouputStream.putNextEntry(entry);
                    // 向压缩文件中输出数s据
                    int nNumber;
                    byte[] buffer = new byte[512];
                    while ((nNumber = bins.read(buffer)) != -1) {
                        ouputStream.write(buffer, 0, nNumber);
                    }
                    // 关闭创建的流对象
                    bins.close();
                    IN.close();
                } else {
                    try {
                        File[] files = inputFile.listFiles();
                        for (int i = 0; i < files.length; i++) {
                            inputFile(files[i], ouputStream);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void ZipCompress(String inputFile, String outputFile) throws Exception {
        //创建zip输出流
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outputFile));
        //创建缓冲输出流
        BufferedOutputStream bos = new BufferedOutputStream(out);
        File input = new File(inputFile);
        compress(out, bos, input,null);
        bos.close();
        out.close();
    }

    /**
     * @param name 压缩文件名，可以写为null保持默认
     */
    //递归压缩
    public static void compress(ZipOutputStream out, BufferedOutputStream bos, File input, String name) throws IOException {
        if (name == null) {
            name = input.getName();
        }
        //如果路径为目录（文件夹）
        if (input.isDirectory()) {
            //取出文件夹中的文件（或子文件夹）
            File[] flist = input.listFiles();

            if (flist.length == 0)//如果文件夹为空，则只需在目的地zip文件中写入一个目录进入
            {
                out.putNextEntry(new ZipEntry(name + "/"));
            } else//如果文件夹不为空，则递归调用compress，文件夹中的每一个文件（或文件夹）进行压缩
            {
                for (int i = 0; i < flist.length; i++) {
                    compress(out, bos, flist[i], name + "/" + flist[i].getName());
                }
            }
        } else//如果不是目录（文件夹），即为文件，则先写入目录进入点，之后将文件写入zip文件中
        {
            out.putNextEntry(new ZipEntry(name));
            FileInputStream fos = new FileInputStream(input);
            BufferedInputStream bis = new BufferedInputStream(fos);
            int len=-1;
            //将源文件写入到zip文件中
            byte[] buf = new byte[1024];
            while ((len = bis.read(buf)) != -1) {
                bos.write(buf,0,len);
            }
            bis.close();
            fos.close();
        }
    }

    public static void main(String[] args) {
    	//String tmpFilePath = FileUtil.zipFile(files, "张三");
    /*	try {
            ZipCompress("e:\\toExcel", "D:\\toExcel.zip");
           // ZipUncompress("D:\\ytt.zip","D:\\ytt的解压文件");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    //	boolean success = (new File("e:\\toExcel")).delete();
 
	}
    private static void removeDir(File dir) {
    	File[] files=dir.listFiles();
		for(File file:files){
			if(file.isDirectory()){
				removeDir(file);
			}else{
				System.out.println(file+":"+file.delete());
			}
		}
		//System.out.println(dir+":"+dir.delete());

        // 目录此时为空，可以删除
        //return dir.delete();
    }
}
