package me.noverify.list;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import me.noverify.utils.DisplayUtils;
import me.noverify.utils.TextUtils;

public class SearchListEntry extends ListEntry {
	private ClassNode cn;
	private MethodNode mn;
	private String found;
	private String text;

	public SearchListEntry(ClassNode cn, MethodNode mn, String found) {
		super(TextUtils.toHtml(DisplayUtils.getDisplayClass(cn.name) + "." + TextUtils.escape(mn.name) + " - "
				+ TextUtils.addTag("\"" + found + "\"", "font color=#559955")));
		this.cn = cn;
		this.mn = mn;
		this.found = found;
	}

	public SearchListEntry(ClassNode cn, MethodNode mn) {
		super(TextUtils.toHtml(DisplayUtils.getDisplayClass(cn.name) + "." + TextUtils.escape(mn.name)));
		this.cn = cn;
		this.mn = mn;
		this.found = "";
	}

	public SearchListEntry(ClassNode cn, MethodNode mn, String owner, String name, String desc, String opcode) {
		super(TextUtils.toHtml(DisplayUtils.getDisplayClass(cn.name) + "." + TextUtils.escape(mn.name) + " - " + TextUtils.toBold(opcode)
				+ " " + DisplayUtils.getDisplayClassRed(owner) + "." + TextUtils.escape(name) + "(" + DisplayUtils.getDisplayArgs(TextUtils.escape(desc)) + ")"));
		this.cn = cn;
		this.mn = mn;
		this.found = owner + "." + name + desc;
	}

	public ClassNode getCn() {
		return cn;
	}

	public void setCn(ClassNode cn) {
		this.cn = cn;
	}

	public MethodNode getMn() {
		return mn;
	}

	public void setMn(MethodNode mn) {
		this.mn = mn;
	}

}
