package com.greenhi.common.exception;

import com.greenhi.common.response.Status;

/**
 * 어플리케이션 오류 처리
 * 
 * @author  kimyu
 * @version 1.0, 7/24/2014
 * @since   1.5
 */
public class ApplicationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7215015415858243573L;

	/**
	 * 상태코드
	 * 
	 * 200 : 성공
	 * 400 : 실패
	 * 401 : 비번오류(로그인 시), 인증실패
	 * 404 : 이메일오류(로그인 시), 이메일중복(회원가입 시), 회원미존재
	 * 500 : 서버오류
	 */
	private Status status;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	/**
	 * status에 해당하는 메시지 정보의 {} 정보들
	 */
	private String[] messageArgs;
	
	public String[] getMessageArgs() {
		return messageArgs;
	}

	public void setMessageArgs(String[] messageArgs) {
		this.messageArgs = messageArgs;
	}

	/**
	 * Constructs a new exception with <code>null</code> as its detail message.
	 * The cause is not initialized, and may subsequently be initialized by a
	 * call to {@link #initCause}.
	 * 
	 * @param status {@see com.greenhi.common.response.ResponseVO}
	 */
	public ApplicationException(Status status) {
		super();
		this.status = status;
	}

	/**
	 * Constructs a new exception with the specified detail message.  The
	 * cause is not initialized, and may subsequently be initialized by
	 * a call to {@link #initCause}.
	 *
	 * @param status {@see com.greenhi.common.response.ResponseVO}
	 * @param   message   the detail message. The detail message is saved for 
	 *          later retrieval by the {@link #getMessage()} method.
	 */
	public ApplicationException(Status status, String message) {
		super(message);
		this.status = status;
	}

	/**
	 * Constructs a new exception with the specified detail message and
	 * cause.  <p>Note that the detail message associated with
	 * <code>cause</code> is <i>not</i> automatically incorporated in
	 * this exception's detail message.
	 *
	 * @param status {@see com.greenhi.common.response.ResponseVO}
	 * @param  message the detail message (which is saved for later retrieval
	 *         by the {@link #getMessage()} method).
	 * @param  cause the cause (which is saved for later retrieval by the
	 *         {@link #getCause()} method).  (A <tt>null</tt> value is
	 *         permitted, and indicates that the cause is nonexistent or
	 *         unknown.)
	 * @since  1.4
	 */
	public ApplicationException(Status status, String message, Throwable cause) {
		super(message, cause);
		this.status = status;
	}

	/**
	 * Constructs a new exception with the specified cause and a detail
	 * message of <tt>(cause==null ? null : cause.toString())</tt> (which
	 * typically contains the class and detail message of <tt>cause</tt>).
	 * This constructor is useful for exceptions that are little more than
	 * wrappers for other throwables (for example, {@link
	 * java.security.PrivilegedActionException}).
	 *
	 * @param status {@see com.greenhi.common.response.ResponseVO}
	 * @param  cause the cause (which is saved for later retrieval by the
	 *         {@link #getCause()} method).  (A <tt>null</tt> value is
	 *         permitted, and indicates that the cause is nonexistent or
	 *         unknown.)
	 * @since  1.4
	 */
	public ApplicationException(Status status, Throwable cause) {
		super(cause);
		this.status = status;
	}
}
