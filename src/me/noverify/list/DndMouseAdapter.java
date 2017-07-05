package me.noverify.list;

import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

import me.noverify.JByteMod;

public class DndMouseAdapter extends MouseInputAdapter {
	private boolean mouseDragging = false;
	private int dragSourceIndex;
	private JList myList;
	private DefaultListModel myListModel;

	public DndMouseAdapter(JList list) {
		this.myList = list;
		this.myListModel = (DefaultListModel) myList.getModel();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			dragSourceIndex = myList.getSelectedIndex();
			mouseDragging = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(mouseDragging) {
			Object dragElement = myListModel.get(dragSourceIndex);
			if(dragElement instanceof InsnListEntry) {
				((InsnListEntry)dragElement).dragging = false;
			}
			mouseDragging = false;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (mouseDragging && JByteMod.instance.editorDnd()) {
			int currentIndex = myList.locationToIndex(e.getPoint());
			if (currentIndex != dragSourceIndex) {
				int dragTargetIndex = myList.getSelectedIndex();
				Object dragElement = myListModel.get(dragSourceIndex);
				if(dragElement instanceof InsnListEntry) {
					((InsnListEntry)dragElement).dragging = true;
				}
				myListModel.remove(dragSourceIndex);
				myListModel.add(dragTargetIndex, dragElement);
				dragSourceIndex = currentIndex;
			}
		}
	}
}