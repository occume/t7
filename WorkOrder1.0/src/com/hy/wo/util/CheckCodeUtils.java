package com.hy.wo.util;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.you9.base.Globe;

/**
 * <p>验证码工具类.</p>
 * <p>版权信息: Copyright 2006 &copy; 9you.com</p>
 * @author J.et 
 * @version 1.0
 */
public class CheckCodeUtils {
	
	/**
	 * 生成验证码
	 * 
	 * @param response
	 * @return 验证码图片的字符串
	 * @throws IOException
	 */
	public static String makeCheckCode(HttpServletResponse response)throws IOException {
		BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT,
				BufferedImage.TYPE_BYTE_INDEXED);
		Graphics g = image.getGraphics();// 获取图形上下文
		makeBKGImage(image, g);// 生成背景图片
		String imageStr = makeCodeImage(g);// 生成验证码图片
		g.dispose();// 图象生效
		ImageIO.write(image, "JPEG", response.getOutputStream());// 输出图象到页面
		return imageStr;
	}

	/**
	 * 生成验证码图片
	 * @param g
	 * @return 验证码图片
	 */
	private static String makeCodeImage(Graphics g) {
		short codeType = getCheckCodeType();// 获取使用的验证码类型
		String imageStr = null;
		String[] checkCodeCountList = Globe.getProperty(
				GlobalNodeName.CHECK_CODE_COUNT).split(COMMA);// 获取生成验证码字符的个数集
		int checkCodeCount = Integer.valueOf(checkCodeCountList[RANDOM
				.nextInt(checkCodeCountList.length)]);// 随机获取生成验证码的个数
		if (codeType == CheckCode.FLOAT_PONIT_TYPE) {// 生成前景图片为浮点样式的验证码
			imageStr = makeCodeFloatPoint(g, checkCodeCount);
		} else if (codeType == CheckCode.OVERLAP_TYPE) {// 生成重叠样式的验证码
			imageStr = makeCodeOverlap(g, checkCodeCount);
		} else if (codeType == CheckCode.DISTORTION_TYPE) { // 生成扭曲样式的验证码
			imageStr = makeCodeDistortion(g, checkCodeCount);
		} else {// 生成验证码类型不存在，抛出异常
			LOGGER.error("参数错误：验证码类型不存在=" + codeType);
		}
		return imageStr;
	}

	/**
	 * 获取使用验证码类型
	 * 
	 * @return
	 */
	private static short getCheckCodeType() {
		short codeType = Short.parseShort(CHECK_CODE_TYPE);// 从配置文件获取使用哪种类型的验证码
		String[] randomTypes = Globe.getProperty(
				GlobalNodeName.CHECK_CODE_TYPE_RANDOMTYPE).split(COMMA);// 全部的验证码类型
		if (codeType == CheckCode.RANDOM_TYPE) {// 如果要求验证码类型是随机型，将从所有验证码类型中随机显示一种
			codeType = Short.parseShort(randomTypes[RANDOM
					.nextInt(randomTypes.length)]);
		} else if (!CheckCodeUtil.indexOfArray(randomTypes, String.valueOf(codeType))) {// 当获取验证码类型不在所规定的所有验证码类型中时
			LOGGER.error("参数错误：验证码类型不存在=" + codeType);
		} else {
		}
		return codeType;
	}

	/**
	 * 生成前景为浮点的验证码
	 * 
	 * @param g
	 * @@return imageStr 生成验证码的字符串
	 */
	public static String makeCodeFloatPoint(Graphics g, int checkCodeCount) {
		String randString = null;
		String imageStr = "";
		String[] fontColor = Globe.getProperty(GlobalNodeName.CODE_FP_COLOR)
				.split(COMMA);// 从配置文件中获取验证码字体颜色
		Font font = new Font(FONTS[0], FONT_STYLE[0], Integer.parseInt(Globe
				.getProperty(GlobalNodeName.CODE_FP_FONT_SIZE)));
		String[] FP_FG_COLOR = Globe.getProperty(
				GlobalNodeName.CODE_FP_FG_COLOR).split(COMMA);// 获取floatPoint验证码前景图的颜色
		for (int i = 0; i < checkCodeCount; i++) {
			randString = CheckCodeUtil.getRandomString();
			// 将验证码显示到图象中
			g.setColor(new Color(Integer.valueOf(fontColor[0]), Integer
					.valueOf(fontColor[1]), Integer.valueOf(fontColor[2])));
			g.setFont(font);
			int x = Integer.valueOf(Globe.getProperty(GlobalNodeName.CODE_FP_X))
					* i+ RANDOM.nextInt(Integer.valueOf(Globe
					.getProperty(GlobalNodeName.CODE_FP_RANDOMX)))
					+ Integer.valueOf(Globe
					.getProperty(GlobalNodeName.CODE_FP_X_ADD));// 生成X坐标值
			int y = Integer.valueOf(Globe.getProperty(GlobalNodeName.CODE_FP_Y))
					+ RANDOM.nextInt(Integer.valueOf(Globe
					.getProperty(GlobalNodeName.CODE_FP_RANDOMY)));// 生成Y坐标值
			g.drawString(randString, x, y);
			for (int j = 0, n = RANDOM.nextInt(20); j < n; j++) { // 画图片前景点
				g.setColor(new Color(Integer.valueOf(FP_FG_COLOR[0]), Integer
						.valueOf(FP_FG_COLOR[1]), Integer
						.valueOf(FP_FG_COLOR[2])));
				g.drawRect(RANDOM.nextInt(IMAGE_WIDTH), RANDOM
						.nextInt(IMAGE_HEIGHT), ONE, ONE);
			}
			imageStr += randString;
		}
		return imageStr;
	}

	/**
	 * 生成字体重叠的验证码
	 * 
	 * @param g
	 * @@return imageStr 生成验证码的字符串
	 */
	public static String makeCodeOverlap(Graphics g, int checkCodeCount) {
		String randString = null;
		String imageStr = "";
		Font OddFont = new Font(FONTS[0], FONT_STYLE[0], Integer.parseInt(Globe
				.getProperty(GlobalNodeName.CONDE_OL_ODDFONT_SIZE)));// 奇数字体颜色
		Font EvenFont = new Font(FONTS[0], FONT_STYLE[2], Integer
				.parseInt(Globe.getProperty(GlobalNodeName.CONDE_OL_EVENFONT_SIZE)));// 偶数字体颜色
		String[] OddFColor = Globe.getProperty(
				GlobalNodeName.CONDE_OL_FCOLOR_ODD).split(COMMA);// 重叠字体验证码奇数字体颜色
		String[] EvenFColor = Globe.getProperty(
				GlobalNodeName.CONDE_OL_FCOLOR_EVEN).split(COMMA);// 重叠字体验证码偶数字体颜色
		for (int i = 0; i < checkCodeCount; i++) {
			randString = CheckCodeUtil.getRandomString();
			if (i % TWO == ZERO) {
				g.setColor(new Color(Integer.valueOf(OddFColor[0]), Integer
						.valueOf(OddFColor[1]), Integer.valueOf(OddFColor[2])));
				g.setFont(EvenFont);
			} else {
				g.setColor(new Color(Integer.valueOf(EvenFColor[0]),
								Integer.valueOf(EvenFColor[1]),
								Integer.valueOf(EvenFColor[2])));
				g.setFont(OddFont);
			}
			int x = Integer.valueOf(Globe
					.getProperty(GlobalNodeName.CODE_OVERLAP_X))
					* i+ RANDOM.nextInt(Integer.valueOf(Globe
							.getProperty(GlobalNodeName.CODE_OVERLAP_RANDOMX)));
			int y = RANDOM.nextInt(Integer.valueOf(Globe
					.getProperty(GlobalNodeName.CODE_OVERLAP_RANDOMY)))
					+ Integer.valueOf(Globe
							.getProperty(GlobalNodeName.CODE_OVERLAP_Y));
			g.drawString(randString, x, y);
			imageStr += randString;
		}
		return imageStr;
	}

	/**
	 * 生成字体变形样式的验证码
	 * @param g
	 * @return imageStr 生成验证码的字符串
	 */
	public static String makeCodeDistortion(Graphics g, int checkCodeCount) {
		String randString = null;
		String imageStr = "";
		Font fontCourierBold = new Font(FONTS[0], FONT_STYLE[0], Integer
				.parseInt(Globe.getProperty(GlobalNodeName.CODE_DIS_FONT_SIZE)));
		String[] fontColor = Globe.getProperty(GlobalNodeName.CODE_DIS_FCOLOR)
				.split(COMMA);// 变形字体验证码字体颜色
		for (int i = 0; i < checkCodeCount; i++) {
			randString = CheckCodeUtil.getRandomString();
			imageStr += randString;
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
			g.setFont(fontCourierBold);
			g.setColor(new Color(Integer.valueOf(fontColor[0]), Integer
					.valueOf(fontColor[1]), Integer.valueOf(fontColor[2])));
			g.drawString(imageStr, Integer.valueOf(Globe
					.getProperty(GlobalNodeName.CODE_DISTORTION_X)), Integer
					.valueOf(Globe.getProperty(GlobalNodeName.CODE_DISTORTION_Y)));
		}
		shear(g, IMAGE_WIDTH, IMAGE_HEIGHT, Color.white);
		return imageStr;
	}

	/**
	 * 生成背景图片 
	 * @param image
	 * @param g
	 */
	public static void makeBKGImage(BufferedImage image, Graphics g) {
		g.setColor(new Color(TWO_HUNDRED, TWO_HUNDRED, TWO_HUNDRED));
		g.fillRect(ZERO, ZERO, IMAGE_WIDTH, IMAGE_HEIGHT);
		int IF_LINES_NUMBER = Integer.valueOf(Globe
				.getProperty(GlobalNodeName.BKG_IF_LINES_NUMBER));// 获取验证码背景图片干扰线数量
		String[] BKG_COLOR_NUMBER = Globe.getProperty(
				GlobalNodeName.BKG_IF_COLOR).split(COMMA);// 获取生成背景颜色随机值
		int LF_LINES_X1 = Integer.valueOf(Globe
				.getProperty(GlobalNodeName.BKG_IF_LINES_X1));// 验证码背景图片干扰线X1值
		int LF_LINES_Y1 = Integer.valueOf(Globe
				.getProperty(GlobalNodeName.BKG_IF_LINES_Y1));// 验证码背景图片干扰线Y1值
		// 随机产生155条干扰线
		g.setColor(getRandColor(Integer.valueOf(BKG_COLOR_NUMBER[0].trim()),
				Integer.valueOf(BKG_COLOR_NUMBER[1].trim())));
		for (int i = 0; i < IF_LINES_NUMBER; i++) {
			int x = RANDOM.nextInt(IMAGE_WIDTH);
			int y = RANDOM.nextInt(IMAGE_HEIGHT);
			int xl = RANDOM.nextInt(LF_LINES_X1);
			int yl = RANDOM.nextInt(LF_LINES_Y1);
			g.drawLine(x, y, x + xl, y + yl);
		}
	}

	/**
	 * 画图片座标
	 * 
	 * @param g
	 * @param w1
	 * @param h1
	 * @param color
	 */
	public static void shear(Graphics g, int w1, int h1, Color color) {
		shearX(g, w1, h1, color);
		shearY(g, w1, h1, color);
	}

	/**
	 * 画图片的X座标
	 * 
	 * @param g
	 * @param w1
	 * @param h1
	 * @param color
	 */
	public static void shearX(Graphics g, int w1, int h1, Color color) {
		int period = RANDOM.nextInt(TWO);
		boolean borderGap = true;
		int frames = 1;
		int phase = RANDOM.nextInt(TWO);

		for (int i = 0; i < h1; i++) {
			double d = (double) (period >> ONE)
					* Math.sin((double) i / (double) period
							+ (sixD * (double) phase) / (double) frames);
			g.copyArea(ZERO, i, w1, ONE, (int) d, ZERO);
			if (borderGap) {
				g.setColor(color);
				g.drawLine((int) d, i, ZERO, i);
				g.drawLine((int) d + w1, i, w1, i);
			}
		}
	}

	/**
	 * 画图片的Y座标
	 * 
	 * @param g
	 * @param w1
	 * @param h1
	 * @param color
	 */
	public static void shearY(Graphics g, int w1, int h1, Color color) {
		int period = RANDOM.nextInt(TEN) + TEN; // 50;
		boolean borderGap = true;
		int frames = 20;
		int phase = SEVEN;
		for (int i = 0; i < w1; i++) {
			double d = (double) (period >> 1)
					* Math.sin((double) i / (double) period
							+ (sixD * (double) phase) / (double) frames);
			g.copyArea(i, ZERO, ONE, h1, ZERO, (int) d);
			if (borderGap) {
				g.setColor(color);
				g.drawLine(i, (int) d, i, ZERO);
				g.drawLine(i, (int) d + h1, i, h1);
			}
		}
	}

	/**
	 * 生成随机颜色
	 */
	public static Color getRandColor(int fc, int bc) {
		if (fc > TWO_FIVE_FIVE) {
			fc = TWO_FIVE_FIVE;
		}
		if (bc > TWO_FIVE_FIVE) {
			bc = TWO_FIVE_FIVE;
		}
		int randInt = bc - fc;
		int r = fc + RANDOM.nextInt(randInt);
		int g = fc + RANDOM.nextInt(randInt);
		int b = fc + RANDOM.nextInt(randInt);
		return new Color(r, g, b);
	}
	private static final Random RANDOM = new Random(); // 产生随机数的对象
	private static final String COMMA= ",";// 逗号
	public static final String[] FONTS = {"Courier","Arial Black","Eras Demi ITC","Forte"};
	public static final int[] FONT_STYLE = { Font.BOLD, Font.ITALIC,
			Font.TRUETYPE_FONT, Font.ROMAN_BASELINE };
	private static Logger LOGGER = Logger.getLogger(CheckCodeUtils.class);
	public static final int TWO_FIVE_FIVE 	= 255;
	public static final int TWO_HUNDRED 		= 200;
	public static final int ZERO 				= 0;
	public static final int ONE 				= 1;
	public static final int TWO 				= 2;
	public static final int THREE 			= 3;
	public static final int FOUR 				= 4;
	public static final int FIVE 				= 5;
	public static final int SIX 				= 6;
	public static final int SEVEN 			= 7;
	public static final int TEN 				= 10;
	public static final int TWELVE 			= 12;
	public static final int ONE_HUNDRED_SIXTY = 160;
	public static final int ONE_HUNDRED_FIFTY_FIVE = 155;
	public static final double sixD = 6.2831853071795862D;
	 /** 获取验证码图片的高度值 */
    public static final short IMAGE_WIDTH = 
    	Short.valueOf(Globe.getProperty("checkCode/width"));
    
    /** 获取验证码图片的宽度值 */
    public static final short IMAGE_HEIGHT = 
    	Short.valueOf(Globe.getProperty("checkCode/height"));
    
    /** 获取使用的验证码类型 */
    public static final String CHECK_CODE_TYPE = 
    	Globe.getProperty(GlobalNodeName.CHECK_CODE_TYPE_VALUE);
    /**
	 * global_config.xml文件里节点名
	 */
	public class GlobalNodeName {
		/**系统基本功能信息*/
		public static final String LOGIN_SSO_SWITCH = "validate/sso";
		public static final String XML_STATE_PATH   = "/globe/state/_"; 
		
		/**后台服务系统名*/
		public static final String LOGIN_CENTER = "loginCenter";
		public static final String CLIENT_ID = "clientId";
		public static final String PARAMKEY = "paramKey";
		public static final String CONNECTION_TIME = "connectionTime";
		public static final String SERVICE_URL = "serviceUrl";
		
		
		public static final String CHECK_CODE = "checkCode";
		public static final String DISTORTION = "/distortion";
		public static final String OVERLAP = "/overlap";
		public static final String IF = "/interference";
		public static final String FP = "/floatPoint";
		
		public static final String SESSION_CHECKCODE_KEY = CHECK_CODE + "/checkcode-name";//将验证码存储在SESSION中name

		public static final String BKG_IF_LINES_NUMBER = 
			CHECK_CODE + IF + "/linesNumber";// 验证码图片背景干扰线数量
		public static final String BKG_IF_COLOR = 
			CHECK_CODE + IF + "/color";// 验证码图片背景干扰线数量
		public static final String BKG_IF_LINES_X1 = 
			CHECK_CODE + IF + "/X1";// 验证码图片背景干扰线x1值
		public static final String BKG_IF_LINES_Y1 = 
			CHECK_CODE + IF + "/Y1";// 验证码图片背景干扰线y1值

		public static final String CHECK_CODE_COUNT = 
			CHECK_CODE + "/count";
		public static final String CHECK_CODE_FONTS = 
			CHECK_CODE + "/fonts";
		public static final String CHECK_CODE_CHARACTER = 
			CHECK_CODE + "/character";
		public static final String CHECK_CODE_TYPE_VALUE = 
			CHECK_CODE + "/type/value";
		public static final String CHECK_CODE_TYPE_RANDOMTYPE = 
			CHECK_CODE + "/type/randomType";
		public static final String CODE_DISTORTION_X = 
			CHECK_CODE + DISTORTION + "/X";
		public static final String CODE_DISTORTION_Y = 
			CHECK_CODE + DISTORTION + "/Y";
		public static final String CODE_OVERLAP_X = 
			CHECK_CODE + OVERLAP + "/X";
		public static final String CODE_OVERLAP_Y = 
			CHECK_CODE + OVERLAP + "/Y";
		public static final String CODE_OVERLAP_RANDOMX = 
			CHECK_CODE + OVERLAP + "/randomX";
		public static final String CODE_OVERLAP_RANDOMY = 
			CHECK_CODE + OVERLAP + "/randomY";

		public static final String CODE_FP_X = 
			CHECK_CODE + FP + "/X";// 获取前景是点的验证码的X轴值
		public static final String CODE_FP_Y = 
			CHECK_CODE + FP + "/Y";// 获取前景是点的验证码的Y轴值
		public static final String CODE_FP_RANDOMX = 
			CHECK_CODE + FP + "/randomX";// 获取前景是点的验证码的X轴的随机值
		public static final String CODE_FP_RANDOMY = 
			CHECK_CODE + FP + "/randomY";// 获取前景是点的验证码的Y轴的随机值
		public static final String CODE_FP_X_ADD = 
			CHECK_CODE + FP + "/additionX";// 获取前景是点的验证码的Y轴的随机值
		public static final String CODE_FP_COLOR = 
			CHECK_CODE + FP + "/fontColor";// 获取前景是点的验证码的字体颜色
		public static final String CODE_FP_FONT_SIZE = 
			CHECK_CODE + FP + "/fontSize";// 获取前景是点的验证码的字体大小
		public static final String CODE_DIS_FCOLOR = 
			CHECK_CODE + DISTORTION + "/fontColor";// 获取变形字体验证码的字体颜色
		public static final String CODE_DIS_FONT_SIZE = 
			CHECK_CODE + DISTORTION + "/fontSize";// 获取变形验证码的字体大小
		public static final String CONDE_OL_FCOLOR_ODD = 
			CHECK_CODE + OVERLAP + "/fontColorOdd";// 获取重叠形字体验证码的字体颜色偶数字符的颜色
		public static final String CONDE_OL_FCOLOR_EVEN = 
			CHECK_CODE + OVERLAP + "/fontColorEven";// 获取重叠形字体验证码的字体颜色奇数字符的颜色
		public static final String CONDE_OL_ODDFONT_SIZE = 
			CHECK_CODE + OVERLAP + "/fontSizeOdd";// 获取重叠形字体验证码的字体颜色奇数字符的颜色
		public static final String CONDE_OL_EVENFONT_SIZE = 
			CHECK_CODE + OVERLAP + "/fontSizeEven";// 获取重叠形字体验证码的字体颜色奇数字符的颜色
		public static final String CODE_FP_FG_COLOR = 
			CHECK_CODE + FP + "/foregroundColor";// 获取前景图片的颜色

	}
	/**
	 * 验证码生成相关参数
	 */
	public class CheckCode {
		public static final int RANDOM_TYPE = 0;// 随机类型验证码
		public static final int FLOAT_PONIT_TYPE = 1;// 前景为浮点型的验证码
		public static final int OVERLAP_TYPE = 2;// 字体重叠型的验证码
		public static final int DISTORTION_TYPE = 3;// 字体变形的验证码
	}
	/**
	 * 工具类
	 */
	public static class CheckCodeUtil{
		/**
		 * 生成显示的字符
		 * 
		 * @return rand 生成一个字符
		 */
		public static String getRandomString() {
			return String.valueOf(CHARS[RANDOM.nextInt(CHARS.length)]);
		}
		/**
		 * 拼接验证码的字符，去除了"o","O","l","I","i"，0，1
		 * @return
		 */
		public static char[] getCheckCodeChars(){
			return Globe
			.getProperty(GlobalNodeName.CHECK_CODE_CHARACTER).toCharArray();
		}
		 /**
		 * 判断字符串是否存在于这个字符串数组里
		 * 
		 * @param columns
		 * @param type
		 * @return boolean [true 存在，false 不存在]
		 */
		public static boolean indexOfArray(String[] columns, String type) {
			boolean isInColumnList = false;
			for (int i = 0; i < columns.length; i++) {
				if (type.equals(columns[i])) {
					isInColumnList = true;
					break;
				}
			}
			return isInColumnList;
		}
		public static final char[] CHARS = getCheckCodeChars();
	}
}
