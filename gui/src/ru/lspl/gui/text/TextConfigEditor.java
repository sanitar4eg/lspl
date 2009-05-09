package ru.lspl.gui.text;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import ru.lspl.gui.model.Document;

/**
 * @author  alno
 */
public class TextConfigEditor extends Composite {
	
	/**
	 * @uml.property  name="document"
	 * @uml.associationEnd  
	 */
	public Document document = null;

	private Button analyzePunctuationCheckBox = null;
	private Button autoReanalyzeCheckBox = null;

	public TextConfigEditor( Composite parent, int style ) {
		super( parent, style );
		initialize();
	}

	private void initialize() {
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = GridData.CENTER;
		
		analyzePunctuationCheckBox = new Button(this, SWT.CHECK);
		analyzePunctuationCheckBox.setText( "Анализировать пунктуацию" );
		analyzePunctuationCheckBox.setLayoutData(gridData);
		analyzePunctuationCheckBox.setSelection( true );
		analyzePunctuationCheckBox.addSelectionListener( new org.eclipse.swt.events.SelectionAdapter() {
			
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				document.getTextConfig().analyzePunctuation = analyzePunctuationCheckBox.getSelection();
				
				if ( document.autoAnalyze )
					document.analyze();
			}
			
		});
		
		autoReanalyzeCheckBox = new Button(this, SWT.CHECK);
		autoReanalyzeCheckBox.setText( "Автоматически анализировать текст при изменениях" );
		autoReanalyzeCheckBox.setLayoutData(gridData);
		autoReanalyzeCheckBox.addSelectionListener( new org.eclipse.swt.events.SelectionAdapter() {
			
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				document.autoAnalyze = autoReanalyzeCheckBox.getSelection();
			}
			
		});
		
		setSize( new Point( 300, 200 ) );
		setLayout( new GridLayout() );
	}

	/**
	 * @return
	 * @uml.property  name="document"
	 */
	public Document getDocument() {
		return document;
	}

	/**
	 * @param doc
	 * @uml.property  name="document"
	 */
	public void setDocument( Document doc ) {
		document = doc;
		
		analyzePunctuationCheckBox.setSelection( document.getTextConfig().analyzePunctuation );
	}
	
	@Override
	public void setBackground( Color color ) {
		super.setBackground( color );
		analyzePunctuationCheckBox.setBackground( color );
		autoReanalyzeCheckBox.setBackground( color );
	}

}
