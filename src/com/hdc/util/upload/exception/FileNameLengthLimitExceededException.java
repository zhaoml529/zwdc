package com.hdc.util.upload.exception;

import org.apache.commons.fileupload.FileUploadException;

/**
 * 文件名超长
 * @author ZML
 *
 */

public class FileNameLengthLimitExceededException extends FileUploadException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3288197738549638992L;
	private int length;
    private int maxLength;
    private String filename;

    public FileNameLengthLimitExceededException(String filename, int length, int maxLength) {
        super("file name : [" + filename + "], length : [" + length + "], max length : [" + maxLength + "]");
        this.length = length;
        this.maxLength = maxLength;
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public int getLength() {
        return length;
    }


    public int getMaxLength() {
        return maxLength;
    }

}