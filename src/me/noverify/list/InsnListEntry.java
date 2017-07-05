package me.noverify.list;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.MethodNode;

import me.noverify.utils.DisplayUtils;
import me.noverify.utils.TextUtils;

public class InsnListEntry extends ListEntry {
	protected AbstractInsnNode node;
	private MethodNode method;
	public boolean dragging;

	public MethodNode getMethod() {
		return method;
	}

	public InsnListEntry(MethodNode mn, AbstractInsnNode node) {
		super("null");
		this.method = mn;
		this.node = node;
	}

	public AbstractInsnNode getNode() {
		return node;
	}

	public void setNode(AbstractInsnNode node) {
		this.node = node;
	}

	@Override
	public String getText() {
		String txt = DisplayUtils.getNodeText(node);
		return TextUtils.toHtml(dragging ? TextUtils.toItalics(txt) : txt);
	}
}
