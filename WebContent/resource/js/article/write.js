
var editor1 = new toastui.Editor({
  el: document.querySelector('#editor1'),
  height: '600px',
  initialEditType: 'markdown',
  previewStyle: 'vertical',
  initialValue: "# 이곳에 글을 작성해 주세요.",
  plugins: [toastui.Editor.plugin.codeSyntaxHighlight]
});