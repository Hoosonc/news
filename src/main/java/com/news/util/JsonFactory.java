package com.news.util;

import java.util.ArrayList;
import java.util.List;

import com.news.pojo.TreeNode;

public class JsonFactory {
	public static List<TreeNode> buildtree(List<TreeNode> nodes, int id) {
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		for (TreeNode treeNode : nodes) {
			TreeNode node = new TreeNode();
			node.setId(treeNode.getId());
			node.setText(treeNode.getText());
			if (id == treeNode.getFid()) {
				// �ݸ����buildtree������TreeNode�е�children���Ը�ֵ
				node.setChildren(buildtree(nodes, node.getId()));
				treeNodes.add(node);
			}
		}
		return treeNodes;
	}
}
