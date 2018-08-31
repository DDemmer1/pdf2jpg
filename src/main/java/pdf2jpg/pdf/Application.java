package pdf2jpg.pdf;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.util.ImageIOUtil;

public class Application {
	public static int convertedPages = 0;

	public static void main(String[] args) throws IOException {

		
		Scanner reader = new Scanner(System.in);
		System.out.println("***Pdf to JPG***");
		System.out.println("Enter foldername:");
		String path = reader.nextLine(); 
		reader.close();
		
		for(File f : new File(path).listFiles()){
			System.out.println("Konvertiere " + f.getName());
			new Application().convertToImg(f);
		}
	}
	
	
	public void convertToImg(File file) throws IOException{
		
		PDDocument document = PDDocument.loadNonSeq(file, null);
		List<PDPage> pdPages = document.getDocumentCatalog().getAllPages();

		int page = 0;
		for (PDPage pdPage : pdPages) {
			++page;
			System.out.println(page + "/" + document.getNumberOfPages());
			BufferedImage bim = pdPage.convertToImage(BufferedImage.TYPE_INT_RGB, 300);
//			ImageIOUtil.writeImage(bim, file.getName() + "-" + page + ".jpg", 300);
			ImageIOUtil.writeImage(bim, Application.convertedPages + ".jpg", 300);

			Application.convertedPages++;
		}
		document.close();
		
	}

}
