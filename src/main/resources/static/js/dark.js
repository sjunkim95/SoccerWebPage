/**
 * 
 */
 function darkmodeGo () {
  const darkModeToggle = document.getElementById('dn'); // 체크박스 정의
  if (!darkModeToggle) {return !1} // 체크 박스 없을 시 작동 종료
  const Realbody = document.querySelector('body');
  darkModeToggle.addEventListener('change', function(event) {//체크박스의 변화 감지 리스너
    if (!Realbody.classList.contains('darkmode')) { // 바디에 다크모드 클래스가 없으면
      Realbody.classList.add('darkmode'); // 다크모드 추가
 }
    else { // 바디에 다크모드 클래스가 있으면
      Realbody.classList.remove('darkmode'); // 다크모드 클래스를 제거
    }
  })
}
darkmodeGo ()