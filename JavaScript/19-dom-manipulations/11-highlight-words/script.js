const link = document.querySelector('#link');
const boldWords = document.querySelectorAll('.bolded')

link.addEventListener('mouseover', function() {
    boldWords.forEach(word => word.style.fontWeight = 'bold')
})

link.addEventListener('mouseout', function() {
    boldWords.forEach(word => word.style.fontWeight = 'normal')
})